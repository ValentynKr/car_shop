package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

public class PasswordHashingUtil {

    private final Logger LOGGER = Logger.getLogger(PasswordHashingUtil.class.getName());

    public String getSaltedHash(String password) {
        byte[] salt = null;
        try {
            salt = SecureRandom.getInstance(Constant.SHA_1_PRNG).generateSeed(Constant.SALT_LENGTH);
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.severe(Constant.NO_SUCH_ALGORITHM_EXCEPTION_WHILE_HASHING + exception);
        }
        return Base64.encodeBase64String(salt) + Constant.$_SIGN + hash(password, salt);
    }

    private String hash(String password, byte[] salt) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constant.PBKDF_2_WITH_HMAC_SHA_1);
            SecretKey key = factory.generateSecret(new PBEKeySpec(password.toCharArray(), salt, Constant.ITERATIONS,
                    Constant.DESIRED_KEY_LINE));
            return Base64.encodeBase64String(key.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            LOGGER.severe(Constant.NO_SUCH_ALGORITHM_EXCEPTION_WHILE_HASHING + exception);
        }
        return null;
    }

    public boolean check(String password, String stored) {
        String[] saltAndPass = stored.split(Constant.$_REGEX);
        if (saltAndPass.length != 2) {
            throw new RuntimeException(Constant.PASSWORD_HAS_NO_SALT_HASH);
        }
        String inputHash = hash(password, Base64.decodeBase64(saltAndPass[0]));
        return inputHash.equals(saltAndPass[1]);
    }
}