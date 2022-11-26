package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.entity.Captcha;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

public class CaptchaGenerator {

    private final Random random;
    private final Logger LOGGER;

    public CaptchaGenerator() {
        this.random = new Random();
        this.LOGGER = Logger.getLogger(CaptchaGenerator.class.getName());
    }

    public Captcha generate(String captchaId) {
        String captchaValue = getValue();
        StringBuffer captchaValueMixed = getValueWithSymbols(captchaValue);
        BufferedImage imageOfCaptcha = getImage(captchaValueMixed);
        byte[] byteCaptcha = null;
        try {
            byteCaptcha = getByteImage(imageOfCaptcha);
        } catch (IOException exception) {
            LOGGER.severe(Constant.IO_EXCEPTION_APPEARED + exception);
        }
        LOGGER.info(Constant.CAPTCHA_CREATED);
        return new Captcha(captchaId, byteCaptcha, captchaValue);
    }

    private String getValue() {
        StringBuilder stringBuffer = new StringBuilder();
        random.setSeed(System.nanoTime() + 123);
        for (int index = 0; index < Constant.CAPTCHA_VALUE_LENGTH; index++) {
            stringBuffer.append(Constant.RANGE_OF_NUMBERS[random.nextInt(Constant.RANGE_OF_NUMBERS.length)]);
        }
        return stringBuffer.toString();
    }

    private StringBuffer getValueWithSymbols(String captchaValue) {
        StringBuffer stringBuffer = new StringBuffer();
        random.setSeed(System.nanoTime() + 321);
        for (char character : captchaValue.toCharArray()) {
            stringBuffer
                    .append(character)
                    .append(Constant.RANGE_OF_SPECIAL_SYMBOLS[random.nextInt(Constant.RANGE_OF_SPECIAL_SYMBOLS.length)]);
        }
        return stringBuffer;
    }

    private BufferedImage getImage(StringBuffer stringBuffer) {
        String captcha = stringBuffer.toString();
        BufferedImage bufferedImage = new BufferedImage(Constant.PICTURE_WIDTH, Constant.PICTURE_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font(Constant.STYLE_ARIAL, Font.ITALIC, Constant.FONT_SIZE);
        g2d.setFont(font);
        g2d.setColor(new Color(Constant.BOUND_RGB, random.nextInt(Constant.BOUND_RGB),
                random.nextInt(Constant.BOUND_RGB)));
        g2d.drawString(captcha, Constant.X_COORDINATE_PLACEMENT, Constant.Y_COORDINATE_PLACEMENT);
        g2d.dispose();
        return bufferedImage;
    }

    private byte[] getByteImage(BufferedImage bufferedImage) throws IOException {
        byte[] result = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
        if (ImageIO.write(bufferedImage, Constant.TYPE_PNG, imageOutputStream)) {
            result = byteArrayOutputStream.toByteArray();
        }
        imageOutputStream.close();
        byteArrayOutputStream.close();
        return result;
    }
}