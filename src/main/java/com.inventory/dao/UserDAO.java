package com.inventory.dao;

import com.inventory.models.User;
import com.inventory.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * OOP: Data Access Object (DAO) Pattern
 * This class isolates the application's business logic from the persistence layer (database).
 * It handles all CRUD (Create, Read, Update, Delete) operations for the User entity.
 */
public class UserDAO {

    /**
     * Logic: This method searches for a user in the database based on their unique username.
     * @param username The name to look for.
     * @return A User object if found, otherwise null.
     */
    public User findByUsername(String username) throws Exception {

        // Logic: SQL query using a '?' placeholder to prevent SQL Injection attacks.
        String sql = "SELECT * FROM users WHERE username = ?";

        /**
         * Logic: Try-with-resources (Automatic Resource Management)
         * This ensures the Connection and PreparedStatement are closed automatically 
         * after use, preventing database memory leaks.
         */
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Logic: Binds the 'username' parameter to the first '?' in the SQL statement.
            ps.setString(1, username);
            
            // Logic: Executes the query and stores the results in a ResultSet object.
            ResultSet rs = ps.executeQuery();

            /**
             * OOP: Object-Relational Mapping (ORM)
             * Logic: We iterate through the result set. If a record exists, we 
             * manually map the database columns to the User object fields.
             */
            if (rs.next()) {
                User u = new User(); // Creating a new instance of the User Model
                
                // Logic: Extracting data from database columns and setting them in the object.
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setRole(rs.getString("role"));
                
                return u; // Returning the fully populated User object
            }
        }
        // Logic: If the 'if' block never executes, the user doesn't exist; return null.
        return null;
    }
}