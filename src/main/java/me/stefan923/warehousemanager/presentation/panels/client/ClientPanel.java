package me.stefan923.warehousemanager.presentation.panels.client;

import me.stefan923.warehousemanager.presentation.style.StyledJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientPanel extends JPanel {

    protected final JTextField nameTextField = new JTextField(15);
    protected final JTextField addressTextField = new JTextField(15);
    protected final JTextField emailTextField = new JTextField(15);
    protected final JTextField ageTextField = new JTextField(15);

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

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getAgeTextField() {
        return ageTextField;
    }

    public void resetAllFields() {
        nameTextField.setText("");
        addressTextField.setText("");
        emailTextField.setText("");
        ageTextField.setText("");
    }

    private JPanel loadFormPanel() {
        JPanel namePanel = new JPanel();
        namePanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameTextField);

        JPanel addressPanel = new JPanel();
        addressPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        addressPanel.add(new JLabel("Address:"));
        addressPanel.add(addressTextField);

        JPanel emailPanel = new JPanel();
        emailPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailTextField);

        JPanel agePanel = new JPanel(new FlowLayout());
        agePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        agePanel.add(new JLabel("Age:"));
        agePanel.add(ageTextField);

        JPanel firstLayerPanel = new JPanel();
        firstLayerPanel.setLayout(new BoxLayout(firstLayerPanel, BoxLayout.X_AXIS));
        firstLayerPanel.add(namePanel);
        firstLayerPanel.add(addressPanel);

        JPanel secondLayerPanel = new JPanel();
        secondLayerPanel.setLayout(new BoxLayout(secondLayerPanel, BoxLayout.X_AXIS));
        secondLayerPanel.add(emailPanel);
        secondLayerPanel.add(agePanel);

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
