package com.borneobank.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.borneobank.dao.UserDAO;
import com.borneobank.util.PasswordUtil;
import com.borneobank.model.User;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private UserDAO userDAO = new UserDAO();

    public LoginFrame() {
        setTitle("BankEase - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360,220);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.gridx = 0; c.gridy = 0;
        panel.add(new JLabel("Username:"), c);
        c.gridx = 1;
        userField = new JTextField(15);
        panel.add(userField, c);

        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Password:"), c);
        c.gridx = 1;
        passField = new JPasswordField(15);
        panel.add(passField, c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn, c);

        loginBtn.addActionListener(e -> doLogin());

        add(panel);
    }

    private void doLogin() {
        String username = userField.getText().trim();
        char[] pass = passField.getPassword();
        try {
            User u = userDAO.findByUsername(username);
            if (u == null) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
                return;
            }
            String hash = PasswordUtil.hashPassword(pass, u.getSalt());
            if (hash.equals(u.getPasswordHash()) && u.isIsActive()) {
                // success -> open dashboard
                SwingUtilities.invokeLater(() -> {
                    DashboardFrame df = new DashboardFrame(u);
                    df.setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Login error: " + ex.getMessage());
        } finally {
            java.util.Arrays.fill(pass, '\0');
        }
    }
}
