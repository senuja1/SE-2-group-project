package com.inventory.dao;

import com.inventory.models.Item;
import com.inventory.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public List<Item> findAll() throws Exception {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM items";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setQuantity(rs.getInt("quantity"));
            items.add(item);
        }

        return items;
    }
}