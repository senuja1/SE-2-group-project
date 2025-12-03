package com.borneobank.dao;

import com.borneobank.util.DBConnection;
import java.sql.*;
import java.math.BigDecimal;

public class TransactionDAO {
    public void record(long accountId, String type, BigDecimal amount, Long relatedAccount, String desc) throws SQLException {
        String sql = "INSERT INTO transactions(account_id, type, amount, related_account, description) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setLong(1, accountId);
            ps.setString(2, type);
            ps.setBigDecimal(3, amount);
            if (relatedAccount == null) ps.setNull(4, Types.BIGINT);
            else ps.setLong(4, relatedAccount);
            ps.setString(5, desc);
            ps.executeUpdate();
        }
    }
}
