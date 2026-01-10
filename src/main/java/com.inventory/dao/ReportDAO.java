package com.inventory.dao;

import com.inventory.model.ReportItem;
import java.sql.*;
import java.util.*;

public class ReportDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/inventory_db";
    private String jdbcUser = "root";
    private String jdbcPass = "";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }

    public List<ReportItem> getAllItems() {
        List<ReportItem> list = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ReportItem item = new ReportItem();
                item.setItemId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setCategory(rs.getString("category"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ReportItem> getLowStockItems() {
        List<ReportItem> list = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE quantity < 10";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ReportItem item = new ReportItem();
                item.setItemId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setCategory(rs.getString("category"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}