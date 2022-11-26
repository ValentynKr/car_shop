package com.epam.lab.shop.filter.access;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.utility.Parser;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SecurityFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getName());
    private Map<String, List<String>> rolesAndAccessibleResourceMap;
    private List<String> resourceWithAccessRestrictionList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String accessPresetFilePath = filterConfig.getInitParameter(Constant.ACCESS_PRESET_FILE_PATH);
        String accessPresetFileFullPath = filterConfig.getServletContext().getRealPath(accessPresetFilePath);
        Parser parser = new Parser();
        rolesAndAccessibleResourceMap = parser.parseXmlToAccessMap(accessPresetFileFullPath);
        resourceWithAccessRestrictionList = rolesAndAccessibleResourceMap.values()
                .stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        if (!resourceWithAccessRestrictionList.contains(requestURI)) {
            LOGGER.info(Constant.PAGE_IS_NOT_RESTRICTED);
            chain.doFilter(request, response);
        } else {
            if (user == null) {
                LOGGER.info(Constant.USER_IS_NOT_IDENTIFIED);
                chain.doFilter(request, response);
                request.getRequestDispatcher(Constant.LOGIN_JSP).forward(request, response);
            } else {
                if (rolesAndAccessibleResourceMap.get(user.getRole().toString().toLowerCase()).contains(requestURI)) {
                    LOGGER.info(Constant.USER_IS_AUTHORIZED);
                    chain.doFilter(request, response);
                } else {
                    LOGGER.info(Constant.USER_IS_NOT_AUTHORIZED);
                    chain.doFilter(request, response);
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Constant.ACCESS_DENIED_JSP);
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
}