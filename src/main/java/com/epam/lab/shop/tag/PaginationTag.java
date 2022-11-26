package com.epam.lab.shop.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PaginationTag extends SimpleTagSupport {

    private Integer page;
    private String uriForPagination;
    private Integer numberOfPages;
    private Locale currentLocale;
    private Map<String, Map<String, String>> translationMap;
    private Map<String, String> localizedStrings;

    private void initTranslationMap() {
        this.translationMap = new HashMap<>();
        Map<String, String> enTranslation = new HashMap<>();
        enTranslation.put("Previous", "Previous");
        enTranslation.put("Next", "Next");
        Map<String, String> ruTranslation = new HashMap<>();
        ruTranslation.put("Previous", "Предыдущая");
        ruTranslation.put("Next", "Следующая");
        this.translationMap.put("en", enTranslation);
        this.translationMap.put("ru", ruTranslation);
    }

    private Map<String, String> getLocalizedStrings(Locale locale, Map<String, Map<String, String>> translationMap) {
        return translationMap.get(locale.getLanguage());
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getUriForPagination() {
        return uriForPagination;
    }

    public void setUriForPagination(String uriForPagination) {
        this.uriForPagination = uriForPagination;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    private String getStartOfBar() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div>\n")
                .append("<nav aria-label=\"Page navigation example\">\n")
                .append("<ul class=\"pagination justify-content-center\">\n");
        return stringBuilder.toString();
    }

    private String getPreviousButton() {
        StringBuilder stringBuilder = new StringBuilder();
        if (page != 1) {
            stringBuilder.append(String.format
                    ("<li class=\"page-item\"><a class=\"page-link\" href=\"%s&page=%s\">%s</a></li>\n",
                            uriForPagination, page - 1, localizedStrings.get("Previous")));
        } else {
            stringBuilder.append("<li class=\"page-item disabled\">\n")
                    .append(String.format("<a class=\"page-link\" href=\"#\" tabindex=\"-1\">%s</a>\n",
                            localizedStrings.get("Previous")))
                    .append("</li>\n");
        }
        return stringBuilder.toString();
    }

    private String getAllPages() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= numberOfPages; i++) {
            if (page.equals(i)) {
                stringBuilder.append("<li class=\"page-item disabled\">\n")
                        .append(String.format("<a class=\"page-link\" href=\"#\">%d</a></li>\n", i));
            } else {
                stringBuilder.append(String.format("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"%s&page=%d\">%d</a></li>\n", uriForPagination, i, i));
            }
        }
        return stringBuilder.toString();
    }

    private String getNextButton() {
        StringBuilder stringBuilder = new StringBuilder();
        if (page < numberOfPages) {
            stringBuilder.append("<li class=\"page-item\">\n")
                    .append(String.format("<a class=\"page-link\" href=\"%s&page=%d\">%s</a>\n",
                            uriForPagination, page + 1, localizedStrings.get("Next")))
                    .append("</li>\n");
        } else {
            stringBuilder.append("<li class=\"page-item disabled\">\n")
                    .append(String.format("<a class=\"page-link\" href=\"#\" tabindex=\"-1\">%s</a>\n",
                            localizedStrings.get("Next")))
                    .append("</li>\n");
        }
        return stringBuilder.toString();
    }

    private String getEndOfBar() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("</ul>\n")
                .append("</nav>\n")
                .append("</div>\n");
        return stringBuilder.toString();
    }

    @Override
    public void doTag() throws IOException {
        initTranslationMap();
        localizedStrings = getLocalizedStrings(currentLocale, translationMap);
        getJspContext().getOut().write(getStartOfBar() + getPreviousButton() + getAllPages()
                + getNextButton() + getEndOfBar());
    }
}