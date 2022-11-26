package com.epam.lab.shop.filter.language.handler.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.filter.language.handler.AbstractLocaleHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class SessionLocaleHandler extends AbstractLocaleHandler {

    public SessionLocaleHandler(AbstractLocaleHandler defaultLocaleHandler, List<Locale> accessibleAppLocales,
                                Long cookieLifeTime) {
        super(defaultLocaleHandler, accessibleAppLocales, cookieLifeTime);
    }

    @Override
    public Locale getLocaleFromStorage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Constant.LOCALE);
        if (locale == null) {
            if (defaultLocaleHandler != null) {
                return defaultLocaleHandler.getLocaleFromStorage(request);
            }
        }
        return locale;
    }

    @Override
    public void setLocaleIntoStorage(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        request.getSession().setAttribute(Constant.LOCALE, locale);
    }
}