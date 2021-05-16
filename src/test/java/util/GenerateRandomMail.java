package util;

import java.util.Random;

/**
 * The purpose of the class is to generate a random mail in correct format
 */
public class GenerateRandomMail {
    /**
     * Method that can build mail using characters and numbers.
     * It will add "@gmail.com" at the end of the created phrase
     * @return String (random mail)
     */
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr+"@gmail.com";
    }
}
