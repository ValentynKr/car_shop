package com.epam.lab.shop.tag;

import com.epam.lab.shop.constant.Constant;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.logging.Logger;

public class CaptchaTag extends SimpleTagSupport {

    private final Logger LOGGER = Logger.getLogger(CaptchaTag.class.getName());
    private String captchaId;
    private String captchaLocation;

    public String getCaptchaLocation() {
        return captchaLocation;
    }

    public void setCaptchaLocation(String captchaLocation) {
        this.captchaLocation = captchaLocation;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    private String getCaptchaImage() {
        return String.format(Constant.IMAGE_HTML, captchaId);
    }

    private String getHiddenCaptchaId() {
        return String.format(Constant.HIDDEN_ATTRIBUTE_HTML, captchaId);
    }

    @Override
    public void doTag() throws IOException {
        if (Constant.FIELD.equals(captchaLocation)) {
            getJspContext().getOut().write(getCaptchaImage() + getHiddenCaptchaId());
        } else {
            getJspContext().getOut().write(getCaptchaImage());
        }
        LOGGER.info(Constant.CAPTCHA_ID_LOGGER + captchaId);
    }
}