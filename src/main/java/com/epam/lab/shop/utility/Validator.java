package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.UserDTO;
import com.epam.lab.shop.entity.AdditionalOrderData;
import com.epam.lab.shop.entity.Category;
import com.epam.lab.shop.entity.Manufacturer;
import com.epam.lab.shop.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final Logger LOGGER = Logger.getLogger(Validator.class.getName());
    private final UserServiceImpl userServiceImpl;

    public Validator(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public Map<String, String> validateUserDTO(UserDTO userDTO) {
        Map<String, String> validationErr = new HashMap<>();
        if (!isValidName(userDTO.getFirstName())) {
            validationErr.put(Constant.FIRST_NAME, Constant.SHOULD_CONTAIN_MORE_THAT_TWO_CHARACTERS);
        }
        if (!isValidName(userDTO.getSecondName())) {
            validationErr.put(Constant.SECOND_NAME, Constant.SHOULD_CONTAIN_MORE_THAT_TWO_CHARACTERS);
        }
        if (!isValidEmail(userDTO.getEmail())) {
            validationErr.put(Constant.EMAIL, Constant.EMAIL_SHOULD_LOOK_LIKE);
        }
        if (isEmailPresentInDatabase(userDTO.getEmail())) {
            validationErr.put(Constant.EMAIL, Constant.USER_WITH_SUCH_EMAIL_WAS_REGISTERED);
        }
        if (!isValidPhone(userDTO.getPhoneNumber())) {
            validationErr.put(Constant.PHONE, Constant.PHONE_SHOULD_LOOK_LIKE);
        }
        if (!isValidDate(userDTO.getBirthDate())) {
            validationErr.put(Constant.BIRTH_DATE, Constant.DATE_SHOULD_LOOK_LIKE);
        }
        if (!isValidPassword(userDTO.getPassword())) {
            validationErr.put(Constant.PASSWORD, Constant.PASSWORD_SHOULD_CONTAIN);
        }
        if (!isValidRepeatPass(userDTO.getRepeatPassword(), userDTO.getPassword())) {
            validationErr.put(Constant.REPEAT_PASS, Constant.REPEATED_PASSWORD_SHOULD_BE_EQUALS_TO_PASSWORD_ABOVE);
        }
        return validationErr;
    }

    private boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.length() > Constant.MIN_NAME_LENGTH;
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.EMAIL_CHECK_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.PHONE_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private boolean isValidDate(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.DATE_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches() && (Integer.parseInt(date.substring(6, 10)) < Constant.MAX_YEAR
                && Integer.parseInt(date.substring(6, 10)) > Constant.MIN_YEAR);

    }

    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.PASS_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidRepeatPass(String repeatPass, String password) {
        return repeatPass != null && !repeatPass.isEmpty() && repeatPass.equals(password);
    }

    private boolean isEmailPresentInDatabase(String email) {
        return userServiceImpl.getByEmail(email).isPresent();
    }

    public boolean isCarNameValid(String carName) {
        return carName != null && !carName.equals(Constant.EMPTY);
    }

    public boolean isCarManufacturerValid(String manufacturer, HttpServletRequest request) {
        if (manufacturer == null || manufacturer.equals(Constant.EMPTY) || manufacturer.equals(Constant.NOT_SELECTED)) {
            return false;
        }
        List<Manufacturer> manufacturerList =
                (List<Manufacturer>) request.getSession().getAttribute(Constant.MANUFACTURER_LIST);
        for (Manufacturer man : manufacturerList) {
            if (man.getName().equals(manufacturer)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCategoryArrayValid(String[] array, HttpServletRequest request) {
        if (array == null || array.length <= 0) {
            return false;
        }
        List<Category> categoryList = (List<Category>) request.getSession().getAttribute(Constant.CATEGORY_LIST);
        int counter = 0;
        for (String string : array) {
            for (Category cat : categoryList) {
                if (string.equals(cat.getName())) {
                    counter++;
                }
            }
        }
        return counter == array.length;
    }

    public boolean isPriceValid(String price) {
        if (price == null || price.equals(Constant.EMPTY)) {
            return false;
        }
        float result;
        try {
            result = Float.parseFloat(price);
        } catch (NumberFormatException exception) {
            LOGGER.info(Constant.PARSE_EXCEPTION + exception);
            return false;
        }
        return result >= 0;
    }

    public boolean isPriceLogic(Float minPrice, Float maxPrice) {
        if (minPrice == null || maxPrice == null) {
            return true;
        }
        return minPrice <= maxPrice;
    }

    public boolean isSortByValid(String sortBy, HttpServletRequest request) {
        if (sortBy == null || sortBy.equals(Constant.EMPTY) || sortBy.equals(Constant.NOT_SELECTED)) {
            return false;
        }
        Map<String, String> sortingParamsMap =
                (Map<String, String>) request.getSession().getAttribute(Constant.SORTING_PARAMS);
        return sortingParamsMap.containsKey(sortBy);
    }

    public boolean isPageSizeValid(String pageSize) {
        if (pageSize == null || pageSize.equals(Constant.EMPTY)) {
            return false;
        }
        int result;
        try {
            result = Integer.parseInt(pageSize);
        } catch (NumberFormatException exception) {
            LOGGER.info(Constant.PARSE_EXCEPTION + exception);
            return false;
        }
        return result > 0 && result <= 20;
    }

    public boolean isPageValid(String page, HttpServletRequest request) {
        if (page == null || page.equals(Constant.EMPTY)) {
            return false;
        }
        int result;
        try {
            result = Integer.parseInt(page);
        } catch (NumberFormatException exception) {
            LOGGER.info(Constant.PARSE_EXCEPTION + exception);
            return false;
        }
        Integer numberOfPages = (Integer) request.getSession().getAttribute(Constant.NUMBER_OF_PAGES);
        return result > 0 && result <= numberOfPages;
    }

    private boolean isStringValid(String string) {
        Pattern pattern = Pattern.compile(Constant.NEGATIVE_SEARCH_SPECIAL_SYM_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return !matcher.find() && string.length() > 10;
    }

    private boolean isCardValid(String string) {
        Pattern pattern = Pattern.compile(Constant.NEGATIVE_SEARCH_LETTERS_SYMBOLS_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return !matcher.find() && string.length() == 16;
    }

    private boolean isExpDateValid(String string) {
        Pattern pattern = Pattern.compile(Constant.EXPIRATION_DATE_PATTERN_REGEX);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean isCvvValid(String string) {
        Pattern pattern = Pattern.compile(Constant.CVV_CODE_REGEX);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public List<String> getErrorsOfAdditionalOrderDataValidation(AdditionalOrderData additionalOrderData) {
        List<String> errList = new ArrayList<>();
        if (!isStringValid(additionalOrderData.getAddressForDelivery())) {
            errList.add(Constant.ADDRESS_INCORRECT_MSG);
        }
        if (additionalOrderData.getTypeOfPayment().equals(Constant.CARD)) {
            if (!isCardValid(additionalOrderData.getCardCredential())) {
                errList.add(Constant.CARD_NUMBER_INCORRECT_MSG);
            }
            if (!isExpDateValid(additionalOrderData.getExpiringDate())) {
                errList.add(Constant.CARD_EXPIRATION_DATE_INCORRECT_MSG);
            }
            if (!isCvvValid(additionalOrderData.getCvvCode())) {
                errList.add(Constant.CVV_INCORRECT_MSG);
            }
        }
        return errList;
    }
}