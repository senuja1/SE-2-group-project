package com.inventory.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * PasswordUtil is a Utility Class designed using the Singleton-style 
 * static pattern to provide secure password management.
 * * Logic: It uses the Blowfish-based BCrypt algorithm which incorporates 
 * a "salt" to protect against rainbow table attacks.
 */
public class PasswordUtil {

    /**
     * Hashing Function:
     * This method takes a plain text password and transforms it into a 
     * secure hash. 
     * * Logic: BCrypt.gensalt(12) generates a random salt with a cost 
     * factor of 12, making it computationally expensive for hackers 
     * to brute-force.
     * * @param plain The raw password string from the user.
     * @return A hashed string containing the salt and the password signature.
     */
    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(12));
    }

    /**
     * Verification Function:
     * This method compares a raw input password against a stored hash 
     * to verify if they match.
     * * Logic: BCrypt extracts the salt from the 'hashed' string and 
     * applies it to the 'plain' input to see if the resulting signatures match.
     * * @param plain The raw password entered during login.
     * @param hashed The existing hash retrieved from the database.
     * @return boolean True if passwords match, false otherwise.
     */
    public static boolean verify(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}