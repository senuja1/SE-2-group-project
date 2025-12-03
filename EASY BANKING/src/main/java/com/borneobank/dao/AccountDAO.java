package com.borneobank.dao;

import com.borneobank.model.Account;
import com.borneobank.util.DBConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public Account createAccount(int userId, String type, BigDecimal initial) throws SQLException {
        String sql = "INSERT INTO accounts(user_id, balance, account_type) VALUES (?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setBigDecimal(2, initial);
            ps.setString(3, type);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                long id = keys.getLong(1);
                Account acc = new Account();
                acc.setAccountId(id);
                acc.setUserId(userId);
                acc.setBalance(initial);
                acc.setAccountType(type);
                return acc;
            }
        }
        return null;
    }

    public Account getById(long accountId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getLong("account_id"));
                a.setUserId(rs.getInt("user_id"));
                a.setBalance(rs.getBigDecimal("balance"));
                a.setAccountType(rs.getString("account_type"));
                a.setIsFrozen(rs.getBoolean("is_frozen"));
                return a;
            }
        }
        return null;
    }

    public boolean updateBalance(long accountId, BigDecimal newBal) throws SQLException {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setBigDecimal(1, newBal);
            ps.setLong(2, accountId);
            return ps.executeUpdate() == 1;
        }
    }

    // You will later add methods: listAccountsByUser, freezeAccount, etc.
}
