package com.epam.lab.shop.service.captcha.session;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Captcha;
import com.epam.lab.shop.service.captcha.ICaptchaService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.logging.Logger;

public class SessionCaptchaServiceImpl implements ICaptchaService {

    private final Logger LOGGER = Logger.getLogger(SessionCaptchaServiceImpl.class.getName());

    @Override
    public void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(session.getId(), captcha);
        LOGGER.info(String.format(Constant.CAPTCHA_IS_SAVED_USER_SESSION, session.getId()));
    }

    @Override
    public boolean validate(HttpServletRequest request, Map<String,String> validationErrors) {
        Captcha captcha = (Captcha) request.getSession().getAttribute(request.getSession().getId());
        request.getSession().removeAttribute(request.getSession().getId());
        long captchaLifeTime = System.currentTimeMillis() - captcha.getCaptchaCreationTime();
        long captchaTimeout =
                Long.parseLong(request.getServletContext().getAttribute(Constant.CAPTCHA_TIMEOUT).toString());
        if (captchaLifeTime > captchaTimeout) {
            validationErrors.put(Constant.USER_CAPTCHA_MAP, Constant.CAPTCHA_TIME_IS_OUT);
            LOGGER.info(Constant.CAPTCHA_VALIDATION_FAILS_TIME_OUT);
            return false;
        }
        if (captcha.getCaptchaValue().equals(request.getParameter(Constant.CAPTCHA_CODE))) {
            LOGGER.info(Constant.CAPTCHA_VALIDATION_IS_SUCCESSFUL);
            return true;
        } else {
            validationErrors.put(Constant.USER_CAPTCHA_MAP, Constant.VALUE_OF_CAPTCHA_IS_INCORRECT);
            LOGGER.info(Constant.CAPTCHA_VALIDATION_FAILS_CODE_INVALID);
            return false;
        }
    }
}