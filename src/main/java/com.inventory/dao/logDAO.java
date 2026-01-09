package com.se2.groupproject.dao;

import com.se2.groupproject.models.Log;
import com.se2.groupproject.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    public static void saveLog(String username, String action) {
        String sql = "INSERT INTO logs (username, action) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, action);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Log> getAllLogs() {
        List<Log> list = new ArrayList<>();
        String sql = "SELECT * FROM logs ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("id"));
                log.setUsername(rs.getString("username"));
                log.setAction(rs.getString("action"));
                log.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(log);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
