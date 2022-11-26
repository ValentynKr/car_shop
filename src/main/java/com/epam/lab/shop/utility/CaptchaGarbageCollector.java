package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Captcha;
import javax.servlet.ServletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class CaptchaGarbageCollector extends Thread {

    private final Logger LOGGER = Logger.getLogger(CaptchaGarbageCollector.class.getName());
    private final ServletContext context;
    private final long captchaTimeout;
    private final long captchaCollectorInterval;

    public CaptchaGarbageCollector(ServletContext context) {
        this.context = context;
        this.captchaCollectorInterval =
                Long.parseLong(context.getAttribute(Constant.CAPTCHA_COLLECTOR_INTERVAL).toString());
        this.captchaTimeout = Long.parseLong(context.getAttribute(Constant.CAPTCHA_TIMEOUT).toString());
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(captchaCollectorInterval);
                collectOldCaptcha();
            } catch (InterruptedException exception) {
                return;
            }
        }
    }

    private void collectOldCaptcha() {
        Map<String, Captcha> captchaMap = (Map<String, Captcha>) context.getAttribute(Constant.USER_CAPTCHA_MAP);
        if (captchaMap != null && !captchaMap.isEmpty()) {
            Map<String, Captcha> newCaptchaMap = new ConcurrentHashMap<>();
            for (Map.Entry<String, Captcha> entry : captchaMap.entrySet()) {
                Captcha captcha = entry.getValue();
                long captchaLifeTime = System.currentTimeMillis() - captcha.getCaptchaCreationTime();
                if (captchaLifeTime < captchaTimeout) {
                    newCaptchaMap.put(entry.getKey(), entry.getValue());
                }
            }
            context.setAttribute(Constant.USER_CAPTCHA_MAP, newCaptchaMap);
            LOGGER.info(String.format(Constant.CAPTCHA_MAP_CHECKED_AND_CLEANED, captchaMap.size(),
                    newCaptchaMap.size()));
            captchaMap.clear();
        }
    }
}