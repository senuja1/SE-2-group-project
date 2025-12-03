package com.borneobank.dao;

import com.borneobank.model.User;
import com.borneobank.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setSalt(rs.getString("salt"));
                u.setFullName(rs.getString("full_name"));
                u.setRole(rs.getString("role"));
                u.setIsActive(rs.getBoolean("is_active"));
                return u;
            }
        }
        return null;
    }

    public int createUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password_hash, salt, full_name, role) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getSalt());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getRole());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        }
        return -1;
    }
}
