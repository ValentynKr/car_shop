package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.CarFilterDTO;
import java.util.ArrayList;
import java.util.List;

public class RequestBuilder {

    public String buildQueryForCarFilter(CarFilterDTO carFilterDTO) {

        List<String> conditionList = getSQLConditionsFromParams(carFilterDTO);
        StringBuilder stringBuilder = new StringBuilder();
        getTemplateQuery(stringBuilder);
        if (conditionList.size() == 0) {
            getSortingEnding(stringBuilder, carFilterDTO);
            return stringBuilder.toString();
        } else {
            stringBuilder.append(Constant.WHERE);
        }
        if (conditionList.size() > 1) {
            for (int i = 0; i < conditionList.size(); i++) {
                stringBuilder.append(conditionList.get(i));
                if (i != conditionList.size() - 1) {
                    stringBuilder.append(Constant.AND);
                }
            }
        } else {
            stringBuilder.append(conditionList.get(0));
        }
        getSortingEnding(stringBuilder, carFilterDTO);
        return stringBuilder.toString();
    }

    private void getTemplateQuery(StringBuilder stringBuilder) {
        stringBuilder.append(Constant.SELECT_CLAUSE)
                .append(Constant.TABLE_CLAUSE)
                .append(Constant.JOIN_CATEGORY_CLAUSE)
                .append(Constant.JOIN_MANUFACTURER_CLAUSE);
    }

    private void getSortingEnding(StringBuilder stringBuilder, CarFilterDTO carFilterDTO) {
        String sortBy = carFilterDTO.getSortBy();
        Integer pageSize = carFilterDTO.getPageSize();
        Integer page = carFilterDTO.getPage();

        if (sortBy != null) {
            String[] params = sortBy.split(Constant.UNDERSCORE);
            stringBuilder.append(String.format(Constant.ORDER_BY_CLAUSE, params[0], params[1]));
        }
        if (pageSize != null) {
            stringBuilder.append(String.format(Constant.LIMIT_CLAUSE, (page - 1) * pageSize, pageSize));
        }
    }

    private List<String> getSQLConditionsFromParams(CarFilterDTO carFilterDTO) {
        List<String> conditionList = new ArrayList<>();
        if (carFilterDTO.getName() != null) {
            conditionList.add(Constant.LIKE_CLAUSE + carFilterDTO.getName() + Constant.ENDING_LIKE_CLAUSE);
        }
        if (carFilterDTO.getManufacturer() != null) {
            conditionList.add(String.format(Constant.MANUFACTURER_NAME_VALUE, carFilterDTO.getManufacturer()));
        }
        if (carFilterDTO.getCategory() != null) {
            List<String> categoryList = carFilterDTO.getCategory();
            StringBuilder categoriesBuilder = new StringBuilder();
            if (categoryList.size() == 1) {
                conditionList.add(String.format(Constant.CATEGORY_NAME_VALUE, categoryList.get(0)));
            }
            if (categoryList.size() > 1) {
                for (int i = 0; i < categoryList.size(); i++) {
                    if (i == 0) {
                        categoriesBuilder.append(Constant.PARENTHESIS_OPEN);
                    }
                    categoriesBuilder.append(String.format(Constant.CATEGORY_NAME_VALUE,categoryList.get(i)));
                    if (i != categoryList.size() - 1) {
                        categoriesBuilder.append(Constant.OR);
                    }
                    if (i == categoryList.size() - 1) {
                        categoriesBuilder.append(Constant.PARENTHESIS_CLOSE);
                    }
                }
                conditionList.add(categoriesBuilder.toString());
            }
        }
        if (carFilterDTO.getMinPrice() != null) {
            conditionList.add(String.format(Constant.PRICE_MORE_OR_EQ, carFilterDTO.getMinPrice()));
        }
        if (carFilterDTO.getMaxPrice() != null) {
            conditionList.add(String.format(Constant.PRICE_LESS_OR_EQ, carFilterDTO.getMaxPrice()));
        }
        return conditionList;
    }

    public String buildURIForPagination(CarFilterDTO carFilterDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constant.CATALOG_SERVLET_URI);
        if (carFilterDTO.getName() != null) {
            stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.NAME_PARAM,
                    carFilterDTO.getName()));
        }
        if (carFilterDTO.getManufacturer() != null) {
            stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.MANUFACTURER_PARAM,
                    carFilterDTO.getManufacturer()));
        }
        if (carFilterDTO.getCategory() != null) {
            for (String category : carFilterDTO.getCategory()) {
                stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.CATEGORY_PARAM,
                        category));
            }
        }
        if (carFilterDTO.getMinPrice() != null) {
            stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.MIN_PRICE_PARAM,
                    carFilterDTO.getMinPrice()));
        }
        if (carFilterDTO.getMaxPrice() != null) {
            stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.MAX_PRICE_PARAM,
                    carFilterDTO.getMaxPrice()));
        }
        if (carFilterDTO.getSortBy() != null) {
            stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.SORT_BY_PARAM,
                    carFilterDTO.getSortBy()));
        }
        if (carFilterDTO.getPageSize() != null) {
            stringBuilder.append(String.format(Constant.AND_KEY_VALUE_PATTERN_URI, Constant.PAGE_SIZE_PARAM,
                    carFilterDTO.getPageSize()));
        }
        return stringBuilder.toString();
    }
}