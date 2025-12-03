package com.borneobank.app;

import javax.swing.*;
import com.borneobank.model.User;
import com.borneobank.ui.DashboardFrame;

public class main1 {
    public static void main(String[] args) {

        // Create a fake user so Dashboard works
        User fake = new User();
        fake.setUserId(1);
        fake.setUsername("guest");
        fake.setFullName("Guest User");
        fake.setRole("customer");
        fake.setIsActive(true);

        SwingUtilities.invokeLater(() -> {
            DashboardFrame df = new DashboardFrame(fake);
            df.setVisible(true);
        });
    }
}
