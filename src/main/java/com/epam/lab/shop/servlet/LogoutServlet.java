package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refererUrl = request.getHeader(Constant.REFERER);
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.USER);
        session.removeAttribute(Constant.AVATAR_NAME);
        session.invalidate();
        LOGGER.info(Constant.USER_WAS_LOGGED_OUT);
        response.sendRedirect(refererUrl);
    }
}