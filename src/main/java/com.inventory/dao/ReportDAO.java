package com.inventory.dao;

import com.inventory.models.SalesReport;
import com.inventory.models.StockReport;
import com.inventory.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    // 🔥 SALES REPORT (DATE RANGE)
    public static List<SalesReport> getSalesReport(Date from, Date to) {
        List<SalesReport> list = new ArrayList<>();

        String sql = """
            SELECT s.id, i.name, s.quantity, s.total_amount, s.sale_date
            FROM sales s
            JOIN items i ON s.item_id = i.id
            WHERE s.sale_date BETWEEN ? AND ?
            ORDER BY s.sale_date DESC
        """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, from);
            ps.setDate(2, to);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SalesReport(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_amount"),
                        rs.getDate("sale_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 📦 STOCK SUMMARY REPORT
    public static List<StockReport> getStockReport() {
        List<StockReport> list = new ArrayList<>();

        String sql = """
            SELECT id, name, quantity,
            CASE 
                WHEN quantity = 0 THEN 'OUT OF STOCK'
                WHEN quantity < 10 THEN 'LOW STOCK'
                ELSE 'IN STOCK'
            END AS status
            FROM items
        """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new StockReport(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 📊 DASHBOARD TOTALS
    public static double getTotalRevenue() {
        String sql = "SELECT SUM(total_amount) FROM sales";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}