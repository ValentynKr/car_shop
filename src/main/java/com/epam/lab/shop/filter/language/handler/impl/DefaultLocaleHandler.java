package com.epam.lab.shop.filter.language.handler.impl;

import com.epam.lab.shop.filter.language.handler.AbstractLocaleHandler;
import com.epam.lab.shop.utility.LocaleSearcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class DefaultLocaleHandler extends AbstractLocaleHandler {

    public DefaultLocaleHandler(List<Locale> accessibleAppLocales) {
        super(null, accessibleAppLocales, null);
    }

    @Override
    public Locale getLocaleFromStorage(HttpServletRequest request) {
        Enumeration<Locale> requestLocales = request.getLocales();
        Locale chosenLocale;
        Locale locale = null;
        while (requestLocales.hasMoreElements()) {
            chosenLocale = requestLocales.nextElement();
            locale = LocaleSearcher.chooseLocaleFromAccessibleLocales(accessibleAppLocales, chosenLocale);
            if (locale != null) {
                break;
            }
        }
        if (locale == null) {
            if (defaultLocaleHandler != null) {
                return defaultLocaleHandler.getLocaleFromStorage(request);
            }
        }
        return locale;
    }

    @Override
    public void setLocaleIntoStorage(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException();
    }
}