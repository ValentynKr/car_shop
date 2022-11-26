package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.OrderDTO;
import com.epam.lab.shop.entity.AdditionalOrderData;
import com.epam.lab.shop.entity.Basket;
import com.epam.lab.shop.entity.Order;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.factory.OrderFactory;
import com.epam.lab.shop.service.impl.OrderServiceImpl;
import com.epam.lab.shop.utility.Extractor;
import com.epam.lab.shop.utility.Validator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(OrderServlet.class.getName());
    private OrderServiceImpl orderService;
    private OrderFactory orderFactory;
    private Validator validator;
    private Extractor extractor;

    @Override
    public void init() {
        orderService = (OrderServiceImpl) getServletContext().getAttribute(Constant.ORDER_SERVICE);
        orderFactory = (OrderFactory) getServletContext().getAttribute(Constant.ORDER_FACTORY);
        validator = (Validator) getServletContext().getAttribute(Constant.VALIDATOR);
        extractor = (Extractor) getServletContext().getAttribute(Constant.EXTRACTOR);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(Constant.USER);
        if (user != null) {
            getUserOrdersAndPutToSession(request, user);
        }
        response.sendRedirect(Constant.ORDERS_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdditionalOrderData additionalOrderData = extractor.extractAdditionalOrderData(request);
        List<String> errorList = validator.getErrorsOfAdditionalOrderDataValidation(additionalOrderData);
        HttpSession session = request.getSession();
        if (session.getAttribute(Constant.USER) == null) {
            errorList.add(Constant.NOT_LOGGED_IN_MSG);
        }
        if (errorList.size() == 0) {
            removeErrorsAndOrderDetails(request);
            Order order = getDataAndExtractOrder(request, additionalOrderData);
            orderService.save(order);
            session.removeAttribute(Constant.BASKET);
            getUserOrdersAndPutToSession(request, (User) session.getAttribute(Constant.USER));
            LOGGER.info(Constant.USER_MADE_ORDER_BASKET_IS_CLEARED);
            response.sendRedirect(Constant.ORDER);
        } else {
            insertErrorsAndOrderDetails(request, additionalOrderData, errorList);
            LOGGER.info(Constant.USER_COMMITTED_MISTAKES_BASKET_NOT_CHANGED);
            response.sendRedirect(Constant.BASKET);
        }
    }

    private Order getDataAndExtractOrder(HttpServletRequest request, AdditionalOrderData additionalOrderData) {
        User user = (User) request.getSession().getAttribute(Constant.USER);
        Basket basket = (Basket) request.getSession().getAttribute(Constant.BASKET);
        return orderFactory.getOrderFromUserAdditionalOrderDataBasket(user, additionalOrderData, basket);
    }

    private void insertErrorsAndOrderDetails(HttpServletRequest request, AdditionalOrderData additionalOrderData,
                                             List<String> errorList) {
        HttpSession session = request.getSession();
        session.setAttribute(Constant.ORDER_ERROR, errorList);
        session.setAttribute(Constant.ORDER_DETAIL, additionalOrderData);
    }

    private void removeErrorsAndOrderDetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.ORDER_ERROR);
        session.removeAttribute(Constant.ORDER_DETAIL);
    }

    private void getUserOrdersAndPutToSession(HttpServletRequest request, User user) {
        List<OrderDTO> orderDTOList = orderService.getAllByUserIdDTO(user.getId());
        if (orderDTOList.size() > 0) {
            request.getSession().setAttribute(Constant.ORDERS, orderDTOList);
            LOGGER.info(Constant.USER_AUTHORISED_WITH_ORDERS);
        }
    }
}