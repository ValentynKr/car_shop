package com.epam.lab.shop.service.captcha;

import com.epam.lab.shop.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ICaptchaService {

    void save(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

    boolean validate(HttpServletRequest request, Map<String, String> validationErrors);

}
