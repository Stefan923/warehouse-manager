package me.stefan923.ordermanager.presentation.panels.product;

import me.stefan923.ordermanager.presentation.style.StyledJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductPanel extends JPanel {

    protected final JTextField nameTextField = new JTextField(15);
    protected final JTextField priceTextField = new JTextField(15);
    protected final JTextField stockTextField = new JTextField(15);

    private final JButton confirmButton = new StyledJButton("Confirm").getButton();
    private final JButton cancelButton = new StyledJButton("Cancel").getButton();

    public JPanel load() {
        this.setLayout(new BorderLayout());
        this.add(loadFormPanel(), BorderLayout.CENTER);
        this.add(loadButtonsPanel(), BorderLayout.SOUTH);

        return this;
    }

    public void addConfirmButtonListener(ActionListener actionListener) {
        confirmButton.addActionListener(actionListener);
    }

    public void addCancelButtonListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getStockTextField() {
        return stockTextField;
    }

    public void resetAllFields() {
        nameTextField.setText("");
        priceTextField.setText("");
        stockTextField.setText("");
    }

    private JPanel loadFormPanel() {
        JPanel namePanel = new JPanel();
        namePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameTextField);

        JPanel pricePanel = new JPanel();
        pricePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        pricePanel.add(new JLabel("Price:"));
        pricePanel.add(priceTextField);

        JPanel stockPanel = new JPanel();
        stockPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        stockPanel.add(new JLabel("Stock:"));
        stockPanel.add(stockTextField);

        JPanel firstLayerPanel = new JPanel();
        firstLayerPanel.setLayout(new BoxLayout(firstLayerPanel, BoxLayout.X_AXIS));
        firstLayerPanel.add(namePanel);
        firstLayerPanel.add(pricePanel);

        JPanel secondLayerPanel = new JPanel();
        secondLayerPanel.setLayout(new BoxLayout(secondLayerPanel, BoxLayout.X_AXIS));
        secondLayerPanel.add(stockPanel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(firstLayerPanel);
        formPanel.add(secondLayerPanel);

        JPanel finalFormPanel = new JPanel();
        finalFormPanel.add(formPanel);

        return finalFormPanel;
    }

    private JPanel loadButtonsPanel() {
        JPanel confirmButtonPanel = new JPanel();
        confirmButtonPanel.add(confirmButton);

        JPanel cancelButtonPanel = new JPanel();
        cancelButtonPanel.add(cancelButton);
        cancelButton.setBackground(new Color(0xF33333));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(confirmButtonPanel);
        buttonsPanel.add(cancelButtonPanel);

        return buttonsPanel;
    }

}
