package com.epam.lab.shop.filter.gzip;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.filter.gzip.wrapper.GzipServletResponseWrapper;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GzipFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (acceptsGZipEncoding(httpRequest)) {
            GzipServletResponseWrapper gzipServletResponseWrapper = new GzipServletResponseWrapper(httpResponse);
            chain.doFilter(request, gzipServletResponseWrapper);
            gzipServletResponseWrapper.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader(Constant.ACCEPT_ENCODING);
        return acceptEncoding != null && acceptEncoding.contains(Constant.GZIP);
    }

    @Override
    public void destroy() {
    }
}