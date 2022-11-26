package com.epam.lab.shop.filter.language.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public abstract class AbstractLocaleHandler {

    protected AbstractLocaleHandler defaultLocaleHandler;
    protected List<Locale> accessibleAppLocales;
    protected Long cookieLifeTime;

    public AbstractLocaleHandler(AbstractLocaleHandler defaultLocaleHandler, List<Locale> accessibleAppLocales,
                                 Long cookieLifeTime) {
        this.defaultLocaleHandler = defaultLocaleHandler;
        this.accessibleAppLocales = accessibleAppLocales;
        this.cookieLifeTime = cookieLifeTime;
    }

    abstract public Locale getLocaleFromStorage(HttpServletRequest request);

    abstract public void setLocaleIntoStorage(HttpServletRequest request, HttpServletResponse response, Locale locale);

}
