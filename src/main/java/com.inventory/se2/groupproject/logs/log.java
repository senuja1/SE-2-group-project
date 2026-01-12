package com.se2.groupproject.logs;

import java.sql.Timestamp;

public class Log {
    private int id;
    private String username;
    private String action;
    private Timestamp createdAt;

    public Log() {}

    public Log(String username, String action) {
        this.username = username;
        this.action = action;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getAction() { return action; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setAction(String action) { this.action = action; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}