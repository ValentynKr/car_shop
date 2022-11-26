package com.epam.lab.shop.filter.language;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.filter.language.factory.LocaleHandlerFactory;
import com.epam.lab.shop.filter.language.handler.AbstractLocaleHandler;
import com.epam.lab.shop.filter.language.handler.impl.DefaultLocaleHandler;
import com.epam.lab.shop.filter.language.wrapper.LanguageRequestWrapper;
import com.epam.lab.shop.utility.LocaleSearcher;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageFilter implements Filter {

    private AbstractLocaleHandler localeHandler;
    private List<Locale> accessibleAppLocales;
    private Locale defaultAppLocale;

    @Override
    public final void init(FilterConfig filterConfig) throws ServletException {
        initAccessibleAppLocalesFromDescriptor(filterConfig);
        defaultAppLocale = Locale.forLanguageTag(filterConfig.getInitParameter(Constant.DEFAULT));
        initLocaleHandler(filterConfig);
    }

    private void initAccessibleAppLocalesFromDescriptor(FilterConfig filterConfig) {
        accessibleAppLocales = new ArrayList<>();
        String localesInOneString = filterConfig.getInitParameter(Constant.AVAILABLE);
        String[] localesArray = localesInOneString.trim().split(Constant.REGEX);
        for (String locale : localesArray) {
            accessibleAppLocales.add(Locale.forLanguageTag(locale));
        }
    }

    private void initLocaleHandler(FilterConfig filterConfig) {
        String localeStorage = filterConfig.getServletContext().getInitParameter(Constant.LOCALE_STORAGE);
        Long cookieLifeTime = Long.parseLong(filterConfig.getServletContext().getInitParameter(Constant.LOCALE_COOKIE_LIFE_TIME));
        LocaleHandlerFactory localeHandlerFactory = new LocaleHandlerFactory(localeStorage,
                new DefaultLocaleHandler(accessibleAppLocales), accessibleAppLocales, cookieLifeTime);
        localeHandler = localeHandlerFactory.getLocaleHandler();
    }

    @Override
    public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (!isRequestToStaticResource(httpRequest.getRequestURI())) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            putAccessibleLocalesForLanguageTag(httpRequest);
            Locale locale = defineLocale(httpRequest);
            localeHandler.setLocaleIntoStorage(httpRequest, httpResponse, locale);
            Config.set(httpRequest.getSession(), Config.FMT_LOCALE, locale);
            chain.doFilter(new LanguageRequestWrapper(httpRequest, locale), response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isRequestToStaticResource(String path) {
        return path.contains("/css/") || path.contains("/assets/") || path.contains("/js/")
                || path.contains("/user-images/");
    }

    private Locale defineLocale(HttpServletRequest request) {
        Locale locale;
        String lang = request.getParameter(Constant.LANG);
        if (lang == null || lang.length() != 2) {
            locale = localeHandler.getLocaleFromStorage(request);
        } else {
            locale = LocaleSearcher.chooseLocaleFromAccessibleLocales(accessibleAppLocales, new Locale(lang.toLowerCase()));
        }
        if (locale == null) {
            locale = defaultAppLocale;
        }
        return locale;
    }

    private void putAccessibleLocalesForLanguageTag(HttpServletRequest httpRequest) {
        if (httpRequest.getSession().getAttribute(Constant.LOCALES) == null) {
            httpRequest.getSession().setAttribute(Constant.LOCALES, accessibleAppLocales);
        }
    }

    @Override
    public final void destroy() {
    }
}