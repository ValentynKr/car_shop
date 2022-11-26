package com.epam.lab.shop.service.captcha.appcontext;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Captcha;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Logger;

public class CookieCaptchaServiceImpl extends AbstractAppContextCaptchaService {

    private final Logger LOGGER = Logger.getLogger(CookieCaptchaServiceImpl.class.getName());

    @Override
    public void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        ServletContext context = request.getServletContext();
        Map<String, Captcha> captchaMap = (Map<String, Captcha>) context.getAttribute(Constant.USER_CAPTCHA_MAP);
        captchaMap.put(sessionId, captcha);
        context.setAttribute(Constant.USER_CAPTCHA_MAP, captchaMap);
        Cookie cookie = new Cookie(Constant.CAPTCHA_ID, sessionId);
        response.addCookie(cookie);
        LOGGER.info(String.format(Constant.CAPTCHA_IS_SAVED_USER_COOKIES, sessionId));
    }

    @Override
    public boolean validate(HttpServletRequest request, Map<String, String> validationErrors) {
        Cookie[] userCookies = request.getCookies();
        String captchaId = getCaptchaId(userCookies);
        if (captchaId == null) {
            validationErrors.put(Constant.USER_CAPTCHA_MAP, Constant.USE_COOKIES_MSG);
            LOGGER.info(Constant.CAPTCHA_VALIDATION_FAILS_NOT_FOUND_ID);
            return false;
        }
        Map<String, Captcha> captchaMap =
                (Map<String, Captcha>) request.getServletContext().getAttribute(Constant.USER_CAPTCHA_MAP);
        Captcha captcha = captchaMap.get(captchaId);
        if (captcha == null) {
            validationErrors.put(Constant.USER_CAPTCHA_MAP, Constant.CAPTCHA_TIME_IS_OUT);
            LOGGER.info(Constant.CAPTCHA_VALIDATION_FAILS_TIME_OUT);
            return false;
        }
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

    private String getCaptchaId(Cookie[] userCookies) {
        String captchaId = null;
        for (Cookie cookie : userCookies) {
            if (Constant.CAPTCHA_ID.equals(cookie.getName())) {
                captchaId = cookie.getValue();
                break;
            }
        }
        return captchaId;
    }
}