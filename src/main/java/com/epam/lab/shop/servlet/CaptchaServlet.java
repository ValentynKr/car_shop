package com.epam.lab.shop.servlet;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Captcha;
import com.epam.lab.shop.service.captcha.ICaptchaService;
import com.epam.lab.shop.service.captcha.appcontext.CookieCaptchaServiceImpl;
import com.epam.lab.shop.service.captcha.appcontext.HiddenFieldsCaptchaServiceImpl;
import com.epam.lab.shop.service.captcha.session.SessionCaptchaServiceImpl;
import com.epam.lab.shop.utility.CaptchaGenerator;
import org.apache.commons.io.IOUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/captcha"})
public class CaptchaServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(CaptchaServlet.class.getName());
    private CaptchaGenerator captchaGenerator;
    private ICaptchaService captchaService;

    @Override
    public void init() {
        Map<String, ICaptchaService> captchaServiceMap = new HashMap<>();
        captchaServiceMap.put(Constant.SESSION, new SessionCaptchaServiceImpl());
        captchaServiceMap.put(Constant.FIELD, new HiddenFieldsCaptchaServiceImpl());
        captchaServiceMap.put(Constant.COOKIE, new CookieCaptchaServiceImpl());
        captchaService = captchaServiceMap.get(getServletContext().getAttribute(Constant.CAPTCHA_LOCATION).toString());
        captchaGenerator = (CaptchaGenerator) getServletContext().getAttribute(Constant.CAPTCHA_FACTORY);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captcha captcha = captchaGenerator.generate(request.getParameter(Constant.CAPTCHA_ID));
        captchaService.save(captcha, request, response);
        response.setContentType(Constant.CONTENT_TYPE_IMAGE_PNG);
        IOUtils.copy(new ByteArrayInputStream(captcha.getCaptchaByte()), response.getOutputStream());
        LOGGER.info(Constant.CAPTCHA_SERVLET_INVOKED);
    }
}