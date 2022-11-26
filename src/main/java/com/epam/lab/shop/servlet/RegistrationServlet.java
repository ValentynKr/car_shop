package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.UserDTO;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.factory.UserFactory;
import com.epam.lab.shop.service.captcha.ICaptchaService;
import com.epam.lab.shop.service.captcha.appcontext.CookieCaptchaServiceImpl;
import com.epam.lab.shop.service.captcha.appcontext.HiddenFieldsCaptchaServiceImpl;
import com.epam.lab.shop.service.captcha.session.SessionCaptchaServiceImpl;
import com.epam.lab.shop.service.impl.UserServiceImpl;
import com.epam.lab.shop.utility.AvatarManager;
import com.epam.lab.shop.utility.Extractor;
import com.epam.lab.shop.utility.Validator;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private UserServiceImpl userServiceImpl;
    private Validator validator;
    private UserFactory userFactory;
    private Extractor extractor;
    private ICaptchaService captchaService;
    private AvatarManager avatarManager;
    private final Logger LOGGER = Logger.getLogger(RegistrationServlet.class.getName());

    @Override
    public void init() {
        Map<String, ICaptchaService> captchaServiceMap = new HashMap<>();
        captchaServiceMap.put(Constant.SESSION, new SessionCaptchaServiceImpl());
        captchaServiceMap.put(Constant.FIELD, new HiddenFieldsCaptchaServiceImpl());
        captchaServiceMap.put(Constant.COOKIE, new CookieCaptchaServiceImpl());
        captchaService = captchaServiceMap.get(getServletContext().getAttribute(Constant.CAPTCHA_LOCATION).toString());
        userServiceImpl = (UserServiceImpl) getServletContext().getAttribute(Constant.USER_SERVICE);
        userFactory = (UserFactory) getServletContext().getAttribute(Constant.USER_FACTORY);
        extractor = (Extractor) getServletContext().getAttribute(Constant.EXTRACTOR);
        validator = (Validator) getServletContext().getAttribute(Constant.VALIDATOR);
        avatarManager = (AvatarManager) getServletContext().getAttribute(Constant.AVATAR_MANAGER);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO userDTO = extractor.extractUserDTO(request);
        if (request.getParameter(Constant.REFRESH) != null) {
            request.getSession().setAttribute(Constant.USER_DTO, userDTO);
            LOGGER.info(String.format(Constant.REFRESHED_CAPTCHA, userDTO.getFirstName()));
            response.sendRedirect(Constant.REGISTRATION_SERVLET_URI);
            return;
        }
        Map<String,String> validationErrors = validator.validateUserDTO(userDTO);
        if (!captchaService.validate(request, validationErrors) || !validationErrors.isEmpty()) {
            request.getSession().setAttribute(Constant.USER_VALIDATION_ERR, validationErrors);
            request.getSession().setAttribute(Constant.USER_DTO, userDTO);
            LOGGER.info(String.format(Constant.USER_REGISTRATION_FAILS, userDTO.getFirstName()));
            response.sendRedirect(Constant.REGISTRATION_SERVLET_URI);
        } else {
            request.getSession().removeAttribute(Constant.USER_VALIDATION_ERR);
            request.getSession().removeAttribute(Constant.USER_DTO);
            User user = userFactory.getUserFromDTO(userDTO);
            userServiceImpl.save(user);
            avatarManager.getAndSaveAvatarOnServer(request, user.getId());
            request.getSession().setAttribute(Constant.USER, user);
            request.getSession().setAttribute(Constant.AVATAR_NAME, userServiceImpl.getUserAvatarName(user.getId()));
            LOGGER.info(String.format(Constant.USER_REGISTRATION_IS_SUCCESSFUL, userDTO.getFirstName()));
            request.getRequestDispatcher(Constant.SUCCESSFUL_REGISTRATION_URI).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(Constant.CAPTCHA_ID, req.getSession().getId());
        req.getRequestDispatcher(Constant.REGISTRATION_URI).forward(req, resp);
    }
}