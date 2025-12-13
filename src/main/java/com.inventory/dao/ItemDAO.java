package com.inventory.dao;

import com.inventory.models.Item;
import com.inventory.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // SERVER-SIDE VALIDATION
    private void validate(Item item) throws Exception {
        if (item.getName() == null || item.getName().isEmpty())
            throw new Exception("Item name cannot be empty");
        if (item.getSku() == null || item.getSku().isEmpty())
            throw new Exception("SKU cannot be empty");
        if (item.getQuantity() < 0)
            throw new Exception("Quantity cannot be negative");
        if (item.getPrice() < 0)
            throw new Exception("Price cannot be negative");
    }

    public void addItem(Item item) throws Exception {
        validate(item);

        String sql = "INSERT INTO items(sku,name,category,quantity,price,supplier_id,reorder_level) "
                + "VALUES(?,?,?,?,?,?,?)";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getSku());
            ps.setString(2, item.getName());
            ps.setString(3, item.getCategory());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getPrice());
            if (item.getSupplierId() != null)
                ps.setInt(6, item.getSupplierId());
            else
                ps.setNull(6, Types.INTEGER);
            ps.setInt(7, item.getReorderLevel());

            ps.executeUpdate();

            // GET AUTO ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) item.setItemId(rs.getInt(1));
        }
    }

    public Item findById(int id) throws Exception {
        String sql = "SELECT * FROM items WHERE item_id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return mapItem(rs);
        }
        return null;
    }

    public List<Item> findAll() throws Exception {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY item_id DESC";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapItem(rs));
        }
        return list;
    }

    public void updateItem(Item item) throws Exception {
        validate(item);

        String sql = "UPDATE items SET sku=?, name=?, category=?, quantity=?, price=?, supplier_id=?, reorder_level=? "
                + "WHERE item_id=?";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, item.getSku());
            ps.setString(2, item.getName());
            ps.setString(3, item.getCategory());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getPrice());

            if (item.getSupplierId() != null)
                ps.setInt(6, item.getSupplierId());
            else
                ps.setNull(6, Types.INTEGER);

            ps.setInt(7, item.getReorderLevel());
            ps.setInt(8, item.getItemId());

            ps.executeUpdate();
        }
    }

    public void deleteItem(int id) throws Exception {
        String sql = "DELETE FROM items WHERE item_id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Item> findLowStock() throws Exception {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE quantity <= reorder_level";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapItem(rs));
        }
        return list;
    }

    private Item mapItem(ResultSet rs) throws Exception {
        Item i = new Item();
        i.setItemId(rs.getInt("item_id"));
        i.setSku(rs.getString("sku"));
        i.setName(rs.getString("name"));
        i.setCategory(rs.getString("category"));
        i.setQuantity(rs.getInt("quantity"));
        i.setPrice(rs.getDouble("price"));

        int supplierId = rs.getInt("supplier_id");
        if (!rs.wasNull()) i.setSupplierId(supplierId);

        i.setReorderLevel(rs.getInt("reorder_level"));
        return i;
    }
}