package com.epam.lab.shop.filter.language.factory;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.filter.language.handler.AbstractLocaleHandler;
import com.epam.lab.shop.filter.language.handler.impl.CookieLocaleHandler;
import com.epam.lab.shop.filter.language.handler.impl.SessionLocaleHandler;
import java.util.List;
import java.util.Locale;

public class LocaleHandlerFactory {

    private final String localeStorage;
    private final AbstractLocaleHandler defaultLocaleHandler;
    private final List<Locale> accessibleAppLocales;
    private final Long cookieLifeTime;

    public LocaleHandlerFactory(String localeStorage, AbstractLocaleHandler defaultLocaleHandler, List<Locale> accessibleAppLocales,
                                Long cookieLifeTime) {
        this.localeStorage = localeStorage;
        this.defaultLocaleHandler = defaultLocaleHandler;
        this.accessibleAppLocales = accessibleAppLocales;
        this.cookieLifeTime = cookieLifeTime;
    }

    public AbstractLocaleHandler getLocaleHandler() {
        AbstractLocaleHandler localeHandler;
        if (Constant.SESSION.equals(localeStorage)) {
            localeHandler = new SessionLocaleHandler(defaultLocaleHandler, accessibleAppLocales, cookieLifeTime);
        } else {
            localeHandler = new CookieLocaleHandler(defaultLocaleHandler, accessibleAppLocales, cookieLifeTime);
        }
        return localeHandler;
    }
}