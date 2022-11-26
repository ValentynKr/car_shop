package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.CarFilterDTO;
import com.epam.lab.shop.dto.UserDTO;
import com.epam.lab.shop.entity.AdditionalOrderData;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class Extractor {

    public UserDTO extractUserDTO(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(request.getParameter(Constant.FIRST_NAME));
        userDTO.setSecondName(request.getParameter(Constant.SECOND_NAME));
        userDTO.setEmail(request.getParameter(Constant.EMAIL));
        userDTO.setPhoneNumber(request.getParameter(Constant.PHONE));
        userDTO.setBirthDate(request.getParameter(Constant.BIRTH_DATE));
        userDTO.setPassword(request.getParameter(Constant.PASSWORD));
        userDTO.setRepeatPassword(request.getParameter(Constant.REPEAT_PASS));
        userDTO.setSubscription(request.getParameter(Constant.SUBSCRIPTION));
        return userDTO;
    }

    public AdditionalOrderData extractAdditionalOrderData(HttpServletRequest request) {
        AdditionalOrderData additionalOrderData = new AdditionalOrderData();
        additionalOrderData.setTypeOfPayment(request.getParameter(Constant.PAYMENT));
        additionalOrderData.setCardCredential(request.getParameter(Constant.CREDENTIAL));
        additionalOrderData.setExpiringDate(request.getParameter(Constant.EXP_DATE));
        additionalOrderData.setCvvCode(request.getParameter(Constant.CVV));
        additionalOrderData.setTypeOfDelivery(request.getParameter(Constant.DELIVERY));
        additionalOrderData.setAddressForDelivery(request.getParameter(Constant.ADDRESS));
        return additionalOrderData;
    }

    public CarFilterDTO extractCarFilterDTO(HttpServletRequest request, Validator validator) {
        String name = request.getParameter(Constant.NAME_PARAM);
        String manufacturer = request.getParameter(Constant.MANUFACTURER_PARAM);
        String[] category = request.getParameterValues(Constant.CATEGORY_PARAM);
        String minPrice = request.getParameter(Constant.MIN_PRICE_PARAM);
        String maxPrice = request.getParameter(Constant.MAX_PRICE_PARAM);
        String sortBy = request.getParameter(Constant.SORT_BY_PARAM);
        String pageSize = request.getParameter(Constant.PAGE_SIZE_PARAM);
        String page = request.getParameter(Constant.PAGE_PARAM);
        CarFilterDTO carFilterDTO = new CarFilterDTO();
        if (validator.isCarNameValid(name)) {
            carFilterDTO.setName(name.replaceAll(Constant.SPECIAL_CHARS_REGEX, Constant.EMPTY));
        }
        if (validator.isCarManufacturerValid(manufacturer, request)) {
            carFilterDTO.setManufacturer(manufacturer);
        }
        if (validator.isCategoryArrayValid(category, request)) {
            carFilterDTO.setCategory(Arrays.asList(category));
        }
        if (validator.isPriceValid(minPrice)) {
            carFilterDTO.setMinPrice(Float.parseFloat(minPrice));
        }
        if (validator.isPriceValid(maxPrice)) {
            carFilterDTO.setMaxPrice(Float.parseFloat(maxPrice));
        }
        if (!validator.isPriceLogic(carFilterDTO.getMinPrice(), carFilterDTO.getMaxPrice())) {
            carFilterDTO.setMaxPrice(carFilterDTO.getMinPrice());
        }
        if (validator.isSortByValid(sortBy, request)) {
            carFilterDTO.setSortBy(sortBy);
        }
        if (validator.isPageSizeValid(pageSize)) {
            carFilterDTO.setPageSize(Integer.parseInt(pageSize));
        }
        if (validator.isPageValid(page, request)) {
            carFilterDTO.setPage(Integer.parseInt(page));
        }
        return carFilterDTO;
    }
}