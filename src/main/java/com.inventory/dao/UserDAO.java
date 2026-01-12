package com.inventory.dao;

import com.inventory.model.User;
import java.sql.*;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/inventory_db";
    private String jdbcUser = "root";
    private String jdbcPass = "";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }

    public User validateUser(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}