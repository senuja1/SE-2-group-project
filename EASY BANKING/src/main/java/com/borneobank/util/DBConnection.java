package com.borneobank.util;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {
                try (InputStream in = DBConnection.class.getResourceAsStream("/config.properties")) {
                    Properties props = new Properties();
                    props.load(in);
                    String url = props.getProperty("db.url");
                    String user = props.getProperty("db.user");
                    String pass = props.getProperty("db.password");
                    conn = DriverManager.getConnection(url, user, pass);
                } catch (Exception e) {
                    throw new SQLException("Cannot load DB props", e);
                }
            }
            return conn;
        } catch (SQLException e) {
            throw e;
        }
    }
}
