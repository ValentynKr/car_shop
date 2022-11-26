package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.CarFilterDTO;
import com.epam.lab.shop.entity.Car;
import com.epam.lab.shop.service.impl.CarServiceImpl;
import com.epam.lab.shop.service.impl.CategoryServiceImpl;
import com.epam.lab.shop.service.impl.ManufacturerServiceImpl;
import com.epam.lab.shop.utility.Extractor;
import com.epam.lab.shop.utility.RequestBuilder;
import com.epam.lab.shop.utility.Validator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/catalog"})
public class CarCatalogServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(CarCatalogServlet.class.getName());
    private CarServiceImpl carService;
    private CategoryServiceImpl categoryService;
    private ManufacturerServiceImpl manufacturerService;
    private Extractor extractor;
    private Validator validator;
    private RequestBuilder requestBuilder;
    private Map<String, String> sortingParams;

    @Override
    public void init() {
        carService = (CarServiceImpl) getServletContext().getAttribute(Constant.CAR_SERVICE);
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute(Constant.CATEGORY_SERVICE);
        manufacturerService = (ManufacturerServiceImpl) getServletContext().getAttribute(Constant.MANUFACTURER_SERVICE);
        extractor = (Extractor) getServletContext().getAttribute(Constant.EXTRACTOR);
        validator = (Validator) getServletContext().getAttribute(Constant.VALIDATOR);
        requestBuilder = (RequestBuilder) getServletContext().getAttribute(Constant.QUERY_BUILDER);
        initMapWithSortingParams();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter(Constant.CANCEL) != null) {
            request.removeAttribute(Constant.CANCEL);
        }
        putInSessionStaticData(request);
        getFilterParamsAndReceiveCarList(request);
        request.getRequestDispatcher(Constant.CATALOG_JSP_PAGE).forward(request, response);
    }

    private void getFilterParamsAndReceiveCarList(HttpServletRequest request) {
        CarFilterDTO carFilterDTO = extractor.extractCarFilterDTO(request, validator);
        String query = requestBuilder.buildQueryForCarFilter(carFilterDTO);
        List<Car> carList = carService.getFiltered(query);
        String uri = requestBuilder.buildURIForPagination(carFilterDTO);
        LOGGER.info(query);
        request.setAttribute(Constant.FILTER_PARAMS, carFilterDTO);
        request.setAttribute(Constant.URI_FOR_PAGINATION, uri);
        request.setAttribute(Constant.CAR_LIST, carList);
        request.getSession().setAttribute(Constant.NUMBER_OF_PAGES, getNumberOfPages(carService.getNumberOfRecords(),
                carFilterDTO.getPageSize()));
    }

    private void putInSessionStaticData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(Constant.CATEGORY_LIST, categoryService.getAll());
        session.setAttribute(Constant.MANUFACTURER_LIST, manufacturerService.getAll());
        session.setAttribute(Constant.SORTING_PARAMS, sortingParams);
    }

    private int getNumberOfPages(int listSize, int pageSize) {
        int pages = listSize / pageSize;
        pages += (listSize % pageSize) != 0 ? 1 : 0;
        return pages;
    }

    private void initMapWithSortingParams() {
        sortingParams = new HashMap<>();
        sortingParams.put(Constant.NAME_ASC, Constant.NAME_A_Z);
        sortingParams.put(Constant.NAME_DESC, Constant.NAME_Z_A);
        sortingParams.put(Constant.PRICE_ASC, Constant.PRICE_LOW_HIGH);
        sortingParams.put(Constant.PRICE_DESC, Constant.PRICE_HIGH_LOW);
    }
}