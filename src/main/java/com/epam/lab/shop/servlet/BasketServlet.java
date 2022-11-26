package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Basket;
import com.epam.lab.shop.service.impl.CarServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/basket"})
public class BasketServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(BasketServlet.class.getName());
    private CarServiceImpl carService;

    @Override
    public void init() {
        carService = (CarServiceImpl) getServletContext().getAttribute(Constant.CAR_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter(Constant.CANCEL) != null) {
            Basket basket = getBasketFromSession(request);
            basket.clearBasket();
            request.removeAttribute(Constant.CANCEL);
            LOGGER.info(Constant.USER_SKIPPED_ALL_CARS);
        }
        request.getRequestDispatcher(Constant.BASKET_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int carId = Integer.parseInt(request.getParameter(Constant.ID));
        int quantity = Integer.parseInt(request.getParameter(Constant.QUANTITY));
        Basket basket = getBasketFromSession(request);
        if (basket == null) {
            basket = new Basket();
            request.getSession().setAttribute(Constant.BASKET, basket);
            LOGGER.info(Constant.BASKET_OBJECT_CREATED);
        }
        basket.addCarToBasket(carService.getById(carId), quantity);
        LOGGER.info(String.format(Constant.ADDED_ORDERED_CARS_CURRENT_BASKET_SIZE,
                basket.getCarQuantityFromBasket(),
                basket.getTotalCarCostFromBasket()));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Basket basket = getBasketFromSession(request);
        int carId = Integer.parseInt(request.getParameter(Constant.ID));
        basket.deleteCarFromBasket(carService.getById(carId));
        LOGGER.info(String.format(Constant.CAR_WAS_DELETED_FROM_BASKET, carId));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        Basket basket = getBasketFromSession(request);
        int carId = Integer.parseInt(request.getParameter(Constant.ID));
        int operation = Integer.parseInt(request.getParameter(Constant.OPERATION));
        if (operation > 0) {
            basket.addCarToBasket(carService.getById(carId), 1);
            LOGGER.info(String.format(Constant.CAR_QUANTITY_WAS_INCREASED, carId));
        } else {
            basket.decreaseCarQuantity(carService.getById(carId), 1);
            LOGGER.info(String.format(Constant.CAR_QUANTITY_WAS_DECREASED, carId));
        }
    }

    private Basket getBasketFromSession(HttpServletRequest request) {
        return (Basket) request.getSession().getAttribute(Constant.BASKET);
    }
}