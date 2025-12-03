package com.borneobank.ui;

import javax.swing.*;
import java.awt.*;
import com.borneobank.model.User;

public class DashboardFrame extends JFrame {
    private User user;

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("BankEase - Dashboard (" + user.getFullName() + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JLabel welcome = new JLabel("Welcome, " + (user.getFullName() != null ? user.getFullName() : user.getUsername()));
        add(welcome, BorderLayout.NORTH);

        // TODO: build tabs: Accounts, Transfer, Transactions
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Accounts", new JPanel());
        tabs.add("Transfer", new JPanel());
        tabs.add("Transactions", new JPanel());
        add(tabs, BorderLayout.CENTER);
    }
}
