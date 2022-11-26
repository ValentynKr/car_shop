package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.BlackListRecord;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.service.impl.BlackListServiceImpl;
import com.epam.lab.shop.service.impl.UserServiceImpl;
import com.epam.lab.shop.utility.PasswordHashingUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private UserServiceImpl userServiceImpl;
    private BlackListServiceImpl blackListService;
    private PasswordHashingUtil passwordHashingUtil;

    @Override
    public void init() {
        userServiceImpl = (UserServiceImpl) getServletContext().getAttribute(Constant.USER_SERVICE);
        blackListService = (BlackListServiceImpl) getServletContext().getAttribute(Constant.BLACK_LIST_SERVICE);
        passwordHashingUtil = (PasswordHashingUtil) getServletContext().getAttribute(Constant.PASSWORD_HASHING_UTIL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter(Constant.EMAIL);
        String password = request.getParameter(Constant.PASSWORD);
        Optional<User> optionalUser = userServiceImpl.getByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!blackListService.existById(user.getId())) {
                if (passwordHashingUtil.check(password, user.getPassword())) {
                    resetAttemptsAndUpdate(user);
                    putUserIntoSession(request, user);
                } else {
                    int loginAttempts = user.getLoginAttempts();
                    user.setLoginAttempts(++loginAttempts);
                    checkWhetherToBlock(userServiceImpl.update(user));
                    putLoginErrorIntoSession(request, email, password, getErrorMessageWithAttemptsLeft(user));
                }
            } else {
                putLoginErrorIntoSession(request, email, password, Constant.ACCOUNT_WAS_BLOCKED);
            }
        } else {
            putLoginErrorIntoSession(request, email, password, Constant.LOGIN_IS_INCORRECT);
        }
        response.sendRedirect(request.getHeader(Constant.REFERER));
    }

    private String getErrorMessageWithAttemptsLeft(User user) {
        int attemptsLeft = Constant.FAILED_LOGIN_ATTEMPTS_LIMIT - user.getLoginAttempts();
        if (attemptsLeft > 0) {
            return String.format(Constant.PASSWORD_IS_INCORRECT, attemptsLeft);
        } else {
            return Constant.ACCOUNT_WAS_BLOCKED;
        }
    }

    private void checkWhetherToBlock(User user) {
        if (user.getLoginAttempts() == Constant.FAILED_LOGIN_ATTEMPTS_LIMIT) {
            blackListService.save(new BlackListRecord(user.getId(), Constant.LIMIT_EXCEEDED,
                    new Timestamp(System.currentTimeMillis())));
        }
    }

    private void resetAttemptsAndUpdate(User user) {
        user.setLoginAttempts(0);
        userServiceImpl.update(user);
    }

    private void putUserIntoSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(Constant.USER, user);
        request.getSession().setAttribute(Constant.AVATAR_NAME, userServiceImpl.getUserAvatarName(user.getId()));
        request.getSession().removeAttribute(Constant.LOGIN_ERRORS);
        LOGGER.info(Constant.USER_LOGGED_IN_SUCCESSFULLY);
    }

    private void putLoginErrorIntoSession(HttpServletRequest request, String email, String password,
                                          String errorMessage) {
        Map<String, String> loginErrors = new HashMap<>();
        loginErrors.put(Constant.EMAIL, email);
        loginErrors.put(Constant.PASSWORD, password);
        loginErrors.put(Constant.MESSAGE, errorMessage);
        request.getSession().setAttribute(Constant.LOGIN_ERRORS, loginErrors);
        LOGGER.info(Constant.USER_FAILS_LOGIN);
    }
}