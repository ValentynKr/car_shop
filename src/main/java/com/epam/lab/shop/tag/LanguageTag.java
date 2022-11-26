package com.epam.lab.shop.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LanguageTag extends SimpleTagSupport {

    private List<Locale> locales;
    private Locale currentLocale;
    private Map<String, Map<String, String>> translationMap;
    private Map<String, String> localizedStrings;
    private String uriForPagination;

    private void initTranslationMap() {
        this.translationMap = new HashMap<>();
        Map<String, String> enTranslation = new HashMap<>();
        enTranslation.put("Language", "Language");
        enTranslation.put("Version for visually impaired", "Version for visually impaired");
        Map<String, String> ruTranslation = new HashMap<>();
        ruTranslation.put("Language", "Язык");
        ruTranslation.put("Version for visually impaired", "Версия для слабовидящих");
        this.translationMap.put("en", enTranslation);
        this.translationMap.put("ru", ruTranslation);
    }

    private Map<String, String> getLocalizedStrings(Locale locale, Map<String, Map<String, String>> translationMap) {
        return translationMap.get(locale.getLanguage());
    }

    public String getUriForPagination() {
        return uriForPagination;
    }

    public void setUriForPagination(String uriForPagination) {
        this.uriForPagination = uriForPagination;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    private String getStartOfTag() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<li class=\"nav-item dropdown\">\n")
                .append("<a class=\"nav-link dropdown-toggle\" id=\"navbarDropdown\" href=\"#\" role=\"button\"\n")
                .append(String.format("data-bs-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">%s</a>\n",
                        localizedStrings.get("Language")))
                .append("<div class=\"dropdown-menu dropdown-menu-end\" aria-labelledby=\"navbarDropdown\">\n");
        return stringBuilder.toString();
    }

    private String getListOfLanguages() {
        StringBuilder stringBuilder = new StringBuilder();
        if (locales != null) {
            if (uriForPagination.length() == 0) {
                for (Locale locale : locales) {
                    stringBuilder.append(String.format("<a class='dropdown-item' href='?lang=%s'>%s</a>\n",
                            locale.getLanguage(), locale.getDisplayLanguage()));
                }
            } else {
                for (Locale locale : locales) {
                    stringBuilder.append(String.format("<a class='dropdown-item' href='%s&lang=%s'>%s</a>\n",
                            uriForPagination, locale.getLanguage(), locale.getDisplayLanguage()));
                }
            }
        }
        return stringBuilder.toString();
    }

    private String getEndingOfTag() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"dropdown-divider\"></div>\n")
                .append(String.format("<a class=\"dropdown-item\" href=\"#!\">%s</a>\n",
                        localizedStrings.get("Version for visually impaired")))
                .append("</div>\n")
                .append("</li>\n");
        return stringBuilder.toString();
    }

    @Override
    public void doTag() throws IOException {
        initTranslationMap();
        localizedStrings = getLocalizedStrings(currentLocale, translationMap);
        getJspContext().getOut().write(getStartOfTag() + getListOfLanguages() + getEndingOfTag());
    }
}