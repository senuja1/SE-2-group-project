package com.borneobank.controllers;

import com.borneobank.util.DBConnection;
import com.borneobank.dao.TransactionDAO;
import java.sql.*;
import java.math.BigDecimal;

public class BankService {
    private final TransactionDAO txDao = new TransactionDAO();

    public boolean transfer(long fromAccount, long toAccount, BigDecimal amount, String desc) throws SQLException {
        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            // lock both accounts (order by id to avoid deadlock)
            long a = Math.min(fromAccount, toAccount);
            long b = Math.max(fromAccount, toAccount);

            String selectSql = "SELECT account_id, balance FROM accounts WHERE account_id IN (?,?) FOR UPDATE";
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setLong(1, a);
                ps.setLong(2, b);
                ResultSet rs = ps.executeQuery();
                java.math.BigDecimal balFrom = null, balTo = null;
                while (rs.next()) {
                    long id = rs.getLong("account_id");
                    if (id == fromAccount) balFrom = rs.getBigDecimal("balance");
                    if (id == toAccount) balTo = rs.getBigDecimal("balance");
                }

                if (balFrom == null) throw new SQLException("Source account not found");
                if (balTo == null) throw new SQLException("Target account not found");
                if (balFrom.compareTo(amount) < 0) throw new SQLException("Insufficient funds");

                BigDecimal newFrom = balFrom.subtract(amount);
                BigDecimal newTo = balTo.add(amount);

                try (PreparedStatement upd = conn.prepareStatement("UPDATE accounts SET balance=? WHERE account_id=?")) {
                    upd.setBigDecimal(1, newFrom); upd.setLong(2, fromAccount); upd.executeUpdate();
                    upd.setBigDecimal(1, newTo); upd.setLong(2, toAccount); upd.executeUpdate();
                }

                // record txs
                txDao.record(fromAccount, "transfer_out", amount, toAccount, desc);
                txDao.record(toAccount, "transfer_in", amount, fromAccount, desc);

                conn.commit();
                return true;
            }
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
