package com.epam.lab.shop.filter.cache;

import com.epam.lab.shop.filter.cache.wrapper.CacheResponseWrapper;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CacheFilter implements Filter {

    @Override
    public final void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        CacheResponseWrapper wrappedResponse = new CacheResponseWrapper(httpResponse);
        chain.doFilter(request, wrappedResponse);
    }

    @Override
    public final void destroy() {
    }
}