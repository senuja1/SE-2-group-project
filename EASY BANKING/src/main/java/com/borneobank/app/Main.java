package com.borneobank.app;

import javax.swing.SwingUtilities;
import com.borneobank.ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame f = new LoginFrame();
            f.setVisible(true);
        });
    }
}
