package com.epam.lab.shop.filter.language.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class LanguageRequestWrapper extends HttpServletRequestWrapper {

    private final Locale locale;

    public LanguageRequestWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        List<Locale> localeList = new ArrayList<>();
        localeList.add(locale);
        return Collections.enumeration(localeList);
    }
}