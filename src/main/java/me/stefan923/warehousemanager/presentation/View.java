package me.stefan923.warehousemanager.presentation;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JPanel currentPanel;

    public void load() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setContentPane(currentPanel);
        this.pack();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width / 2 - this.getSize().width / 2,
                dimension.height / 2 - this.getSize().height / 2);

        this.setTitle("Warehouse Management");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
        this.getContentPane().removeAll();
        this.setContentPane(currentPanel);
        this.pack();
    }

    public void sendError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }

}
