package dev.bobabuster.chatterbox.utils;

import com.password4j.Password;

public class PasswordUtils {

    public static String hashPassword(String password) {
        return Password.hash(password).addRandomSalt().withArgon2().getResult();
    }
}
