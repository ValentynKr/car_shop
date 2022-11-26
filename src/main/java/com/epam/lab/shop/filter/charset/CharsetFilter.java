package com.epam.lab.shop.filter.charset;

import com.epam.lab.shop.constant.Constant;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;

    public CharsetFilter() {
        this.encoding = null;
    }

    public void init(FilterConfig config) {
        encoding = config.getInitParameter(Constant.REQUEST_ENCODING);
        if (encoding == null) {
            encoding = Constant.UTF_8;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType(Constant.TEXT_HTML_CHARSET_UTF_8);
        response.setCharacterEncoding(Constant.UTF_8);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
