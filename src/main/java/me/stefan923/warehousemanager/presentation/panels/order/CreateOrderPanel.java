package me.stefan923.warehousemanager.presentation.panels.order;

import me.stefan923.warehousemanager.model.Client;
import me.stefan923.warehousemanager.model.Product;
import me.stefan923.warehousemanager.presentation.style.StyledJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateOrderPanel extends JPanel {

    private final JComboBox<String> clientComboBox = new JComboBox<>();
    private final JComboBox<String> productComboBox = new JComboBox<>();
    private final TextField quantityTextField = new TextField(15);

    private final JButton confirmButton = new StyledJButton("Confirm").getButton();
    private final JButton cancelButton = new StyledJButton("Cancel").getButton();

    private List<Client> clients;
    private List<Product> products;

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

    public JComboBox<String> getClientComboBox() {
        return clientComboBox;
    }

    public JComboBox<String> getProductComboBox() {
        return productComboBox;
    }

    public TextField getQuantityTextField() {
        return quantityTextField;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void updateClients(List<Client> clients) {
        this.clients = clients;
        clientComboBox.removeAllItems();
        for (Client client : clients) {
            clientComboBox.addItem(client.getName());
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void updateProducts(List<Product> products) {
        this.products = products;
        productComboBox.removeAllItems();
        for (Product product : products) {
            productComboBox.addItem(product.getName());
        }
    }

    public void resetAllFields() {
        quantityTextField.setText("");
    }

    private JPanel loadFormPanel() {
        JPanel clientPanel = new JPanel();
        clientPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        clientPanel.add(new JLabel("Client:"));
        clientPanel.add(clientComboBox);

        JPanel productPanel = new JPanel();
        productPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        productPanel.add(new JLabel("Product:"));
        productPanel.add(productComboBox);

        JPanel quantityPanel = new JPanel();
        quantityPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        quantityPanel.add(new JLabel("Quantity:"));
        quantityPanel.add(quantityTextField);

        JPanel firstLayerPanel = new JPanel();
        firstLayerPanel.setLayout(new BoxLayout(firstLayerPanel, BoxLayout.X_AXIS));
        firstLayerPanel.add(clientPanel);
        firstLayerPanel.add(productPanel);

        JPanel secondLayerPanel = new JPanel();
        secondLayerPanel.setLayout(new BoxLayout(secondLayerPanel, BoxLayout.X_AXIS));
        secondLayerPanel.add(quantityPanel);

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
