package com.epam.lab.shop.filter.language.handler.impl;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.filter.language.handler.AbstractLocaleHandler;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class CookieLocaleHandler extends AbstractLocaleHandler {

    public CookieLocaleHandler(AbstractLocaleHandler defaultLocaleHandler, List<Locale> accessibleAppLocales,
                               Long cookieLifeTime) {
        super(defaultLocaleHandler, accessibleAppLocales, cookieLifeTime);
    }

    @Override
    public Locale getLocaleFromStorage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Locale locale = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.LOCALE)) {
                    locale = Locale.forLanguageTag(cookie.getValue());
                    break;
                }
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
        Cookie cookie = new Cookie(Constant.LOCALE, locale.toLanguageTag());
        cookie.setPath(Constant.ROOT_URI);
        cookie.setMaxAge(cookieLifeTime.intValue());
        response.addCookie(cookie);
    }
}