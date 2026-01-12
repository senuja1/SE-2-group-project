package com.se2.groupproject.logs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static void saveLog(Log log) {
        String sql = "INSERT INTO logs (username, action) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, log.getUsername());
            ps.setString(2, log.getAction());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Log> getAllLogs() {
        List<Log> list = new ArrayList<>();
        String sql = "SELECT * FROM logs ORDER BY created_at DESC";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
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