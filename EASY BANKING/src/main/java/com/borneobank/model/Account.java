package com.borneobank.model;

import java.math.BigDecimal;

public class Account {

    private long accountId;
    private int userId;
    private BigDecimal balance;
    private String accountType;
    private boolean isFrozen;

    public Account() {}

    // --- Getters & Setters ---
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        this.isFrozen = frozen;
    }

    public void setIsFrozen(boolean frozen) {   // <-- this fixes your error
        this.isFrozen = frozen;
    }

}
