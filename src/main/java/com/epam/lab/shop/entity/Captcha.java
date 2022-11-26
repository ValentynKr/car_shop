package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Captcha implements Serializable {

    private String captchaId;
    private byte[] captchaByte;
    private String captchaValue;
    private long captchaCreationTime;

    public Captcha(String captchaId, byte[] captchaByte, String captchaValue) {
        this.captchaId = captchaId;
        this.captchaByte = captchaByte;
        this.captchaValue = captchaValue;
        this.captchaCreationTime = System.currentTimeMillis();
    }

    public long getCaptchaCreationTime() {
        return captchaCreationTime;
    }

    public void setCaptchaCreationTime(long captchaCreationTime) {
        this.captchaCreationTime = captchaCreationTime;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public byte[] getCaptchaByte() {
        return captchaByte;
    }

    public void setCaptchaByte(byte[] captchaByte) {
        this.captchaByte = captchaByte;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "captchaId='" + captchaId + '\'' +
                ", captchaValue='" + captchaValue + '\'' +
                ", captchaCreationTime=" + captchaCreationTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Captcha captcha = (Captcha) obj;
        return captchaCreationTime == captcha.captchaCreationTime && Objects.equals(captchaId, captcha.captchaId) && Arrays.equals(captchaByte, captcha.captchaByte) && Objects.equals(captchaValue, captcha.captchaValue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(captchaId, captchaValue, captchaCreationTime);
        result = 31 * result + Arrays.hashCode(captchaByte);
        return result;
    }
}
