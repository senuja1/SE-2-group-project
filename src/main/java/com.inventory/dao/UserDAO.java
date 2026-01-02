package com.inventory.dao;

import com.inventory.models.User;
import com.inventory.utils.DBConnection;

import java.sql.*;

public class UserDAO {

    public User login(String username, String password) throws Exception {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            return user;
        }
        return null;
    }
}