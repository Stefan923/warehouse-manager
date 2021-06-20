package me.stefan923.warehousemanager.presentation.panels.product;

import me.stefan923.warehousemanager.model.Product;
import me.stefan923.warehousemanager.presentation.style.StyledJButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowProductsPanel extends JPanel {

    private static final String[] TABLE_COLUMNS = new String[]{ "Name", "Price", "Stock" };

    private final JButton addProductButton = new StyledJButton("Add product").getButton();
    private final JButton editProductButton = new StyledJButton("Edit product").getButton();
    private final JButton deleteProductButton = new StyledJButton("Delete product").getButton();

    private final JButton backButton = new StyledJButton("Back").getButton();

    private final JTable productsTable = new JTable();

    private List<Product> products;

    public JPanel load() {
        this.setLayout(new BorderLayout());
        this.add(loadProductsTable(), BorderLayout.CENTER);
        this.add(loadButtonsPanel(), BorderLayout.SOUTH);

        return this;
    }

    public void updateProductsTable(List<Product> products) {
        this.products = products;

        Object[][] productsData = new Object[products.size()][TABLE_COLUMNS.length];
        for (int i = 0; i < products.size(); ++i) {
            Product product = products.get(i);

            productsData[i][0] = product.getName();
            productsData[i][1] = product.getPrice();
            productsData[i][2] = product.getStock();
        }

        productsTable.setModel(new DefaultTableModel(productsData, TABLE_COLUMNS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        productsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        productsTable.setFillsViewportHeight(true);
    }

    public JTable getProductsTable() {
        return productsTable;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addAddProductButtonListener(ActionListener actionListener) {
        addProductButton.addActionListener(actionListener);
    }

    public void addEditProductButtonListener(ActionListener actionListener) {
        editProductButton.addActionListener(actionListener);
    }

    public void addDeleteProductButtonListener(ActionListener actionListener) {
        deleteProductButton.addActionListener(actionListener);
    }

    public void addSProductButtonListener(ActionListener actionListener) {
        deleteProductButton.addActionListener(actionListener);
    }

    public void addBackButtonListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }


    private JPanel loadProductsTable() {
        JScrollPane productsTableScrollPane = new JScrollPane(productsTable);
        productsTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        productsTableScrollPane.setPreferredSize(new Dimension(540, 240));

        JPanel productsTablePanel = new JPanel(new FlowLayout());
        productsTablePanel.add(productsTableScrollPane);

        return productsTablePanel;
    }

    private JPanel loadButtonsPanel() {
        JPanel addProductButtonPanel = new JPanel();
        addProductButtonPanel.add(addProductButton);

        JPanel editProductButtonPanel = new JPanel();
        editProductButtonPanel.add(editProductButton);

        JPanel deleteProductButtonPanel = new JPanel();
        deleteProductButtonPanel.add(deleteProductButton);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        JPanel centerButtonsPanel = new JPanel();
        centerButtonsPanel.add(addProductButtonPanel);
        centerButtonsPanel.add(editProductButtonPanel);
        centerButtonsPanel.add(deleteProductButtonPanel);

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(centerButtonsPanel, BorderLayout.CENTER);
        buttonsPanel.add(backButtonPanel, BorderLayout.WEST);

        return buttonsPanel;
    }

}
