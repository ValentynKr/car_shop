package com.epam.lab.shop.filter.cache.wrapper;

import com.epam.lab.shop.constant.Constant;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class CacheResponseWrapper extends HttpServletResponseWrapper {

    public CacheResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        ServletResponse response = this.getResponse();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader(Constant.CACHE_CONTROL, Constant.NO_CACHE_MAX_AGE_0);
        return super.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        ServletResponse response = this.getResponse();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader(Constant.CACHE_CONTROL, Constant.NO_CACHE_MAX_AGE_0);
        return super.getWriter();
    }
}