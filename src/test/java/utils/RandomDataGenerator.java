package utils;

import java.util.UUID;

public class RandomDataGenerator {

    public static String generateEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public static String generatePassword(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }

    public static String generateName() {
        return "User_" + UUID.randomUUID().toString().substring(0, 5);
    }
}