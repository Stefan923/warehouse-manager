package me.stefan923.ordermanager.presentation.panels;

import me.stefan923.ordermanager.presentation.style.StyledJButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class HomePagePanel extends JPanel {

    private final JButton clientsButton = new StyledJButton("Manage clients").getButton();
    private final JButton productsButton = new StyledJButton("Manage products").getButton();
    private final JButton offersButton = new StyledJButton("Manage orders").getButton();

    public JPanel load() {
        this.setLayout(new BorderLayout());
        this.add(Objects.requireNonNull(loadLogo()), BorderLayout.NORTH);
        this.add(loadButtonsPanel(), BorderLayout.CENTER);

        return this;
    }

    public void addClientsButtonListener(ActionListener actionListener) {
        clientsButton.addActionListener(actionListener);
    }

    public void addProductsButtonListener(ActionListener actionListener) {
        productsButton.addActionListener(actionListener);
    }

    public void addOrdersButtonListener(ActionListener actionListener) {
        offersButton.addActionListener(actionListener);
    }

    private JPanel loadButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        try {
            buttonsPanel.add(loadButtonPanel(clientsButton, "/icons/client.png"));
            buttonsPanel.add(loadButtonPanel(productsButton, "/icons/product.png"));
            buttonsPanel.add(loadButtonPanel(offersButton, "/icons/order.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buttonsPanel;
    }

    private JPanel loadButtonPanel(JButton button, String filePath) throws IOException {
        JPanel btnPanel = new JPanel(new FlowLayout());
        button.setBackground(new Color(0x22B5E7));
        button.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource(filePath)))
                .getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPanel.add(button);
        btnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return btnPanel;
    }

    private JLabel loadLogo() {
        try {
            JLabel jLabel = new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("/icons/logo.png")))
                    .getScaledInstance(240, 240, Image.SCALE_SMOOTH)));
            jLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

            return jLabel;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
