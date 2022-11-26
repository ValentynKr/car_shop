package com.epam.lab.shop.factory;

import com.epam.lab.shop.constant.Constant;
import com.epam.lab.shop.dto.UserDTO;
import com.epam.lab.shop.entity.User;
import com.epam.lab.shop.utility.PasswordHashingUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class UserFactory {

    private final PasswordHashingUtil passwordHashingUtil;
    private final Logger LOGGER;

    public UserFactory(PasswordHashingUtil passwordHashingUtil) {
        this.passwordHashingUtil = passwordHashingUtil;
        this.LOGGER = Logger.getLogger(UserFactory.class.getName());
    }

    public User getUserFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(removeSpaces(userDTO.getFirstName()));
        user.setSecondName(removeSpaces(userDTO.getSecondName()));
        user.setEmail(removeSpaces(userDTO.getEmail()));
        user.setPhoneNumber(removeSeparators(userDTO.getPhoneNumber()));
        user.setPassword(passwordHashingUtil.getSaltedHash(userDTO.getPassword()));
        try {
            user.setBirthDate(new SimpleDateFormat(Constant.DATE_FORMAT).parse(userDTO.getBirthDate()));
        } catch (ParseException exception) {
            LOGGER.severe(Constant.PARSE_EXCEPTION + exception);
        }
        if (userDTO.getSubscription() != null) {
            user.setSubscription(true);
        }
        return user;
    }

    private String removeSpaces(String string) {
        return string.replaceAll(Constant.LAST_SPACE_REGEX, Constant.EMPTY).replaceAll(Constant.FIRST_SPACE_REGEX, Constant.EMPTY);
    }

    private String removeSeparators(String string) {
        return string.replaceAll(Constant.SEPARATORS_REGEX, Constant.EMPTY);
    }
}