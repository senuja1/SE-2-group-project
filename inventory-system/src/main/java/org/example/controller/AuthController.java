package org.example.controller;

/**
 * OBJECT-ORIENTED PRINCIPLE: ABSTRACTION & ENCAPSULATION
 * This class serves as a REST Controller, abstracting the complexity of 
 * JSON Web Token (JWT) generation away from the client-side application.
 * It encapsulates authentication logic within a single, maintainable module.
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* * @RestController: Combines @Controller and @ResponseBody. 
 * Inherits behavior for handling web requests in a RESTful manner.
 */
@RestController
/* Defines the base URI path for all APIs within this controller class. */
@RequestMapping("/api/auth")
/* Enables Cross-Origin Resource Sharing (CORS) to allow frontend communication. */
@CrossOrigin
public class AuthController {

    /**
     * PRIVATE FIELD (ENCAPSULATION): 
     * The secret key is kept private to prevent external class access.
     * In a production environment, this would be moved to an environment variable.
     */
    private final String SECRET = "inventory_secret_key";

    /**
     * METHOD: login
     * Purpose: Authenticates user credentials and returns a secure JWT.
     * * @param body: A Map representing the JSON request body containing 'username' and 'password'.
     * @return: A Map containing the generated authentication token.
     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {

        // Extracting values from the Request Body Map object
        String username = body.get("username");
        String password = body.get("password");

        /*
         * DATA VALIDATION LOGIC:
         * Uses a hardcoded check for administrative credentials.
         * Implementation follows basic conditional control flow.
         */
        if ("admin".equals(username) && "admin".equals(password)) {

            /*
             * BUILDER PATTERN (OOP):
             * Jwts.builder() uses the Builder design pattern to construct 
             * a complex JWT object step-by-step.
             */
            String token = Jwts.builder()
                    // Set the 'sub' (Subject) claim - identifying the user
                    .setSubject(username)
                    
                    // Set the 'iat' (Issued At) claim - timestamp of creation
                    .setIssuedAt(new Date())
                    
                    // Set the 'exp' (Expiration) claim - valid for 24 hours (86,400,000ms)
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    
                    // SIGNING ALGORITHM: HS256 (HMAC with SHA-256)
                    // Uses the private SECRET key to ensure data integrity
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    
                    // Finalize the construction and convert the object to a String
                    .compact();

            /*
             * OBJECT INSTANTIATION:
             * Creating a new HashMap instance to store the key-value pair for the response.
             */
            Map<String, String> res = new HashMap<>();
            
            // Adding the token to the map object
            res.put("token", token);
            
            // Returns the object, which Spring converts into JSON automatically
            return res;
        }

        /*
         * EXCEPTION HANDLING:
         * Throws a RuntimeException if the 'if' condition fails.
         * This follows the principle of 'Failing Fast' in software design.
         */
        throw new RuntimeException("Invalid credentials");
    }
}
