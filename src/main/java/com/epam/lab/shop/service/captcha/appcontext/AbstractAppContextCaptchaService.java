package com.epam.lab.shop.service.captcha.appcontext;

import com.epam.lab.shop.entity.Captcha;
import com.epam.lab.shop.service.captcha.ICaptchaService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

abstract class AbstractAppContextCaptchaService implements ICaptchaService {

    @Override
    public abstract void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

    @Override
    public abstract boolean validate(HttpServletRequest request, Map<String, String> validationErrors);

}