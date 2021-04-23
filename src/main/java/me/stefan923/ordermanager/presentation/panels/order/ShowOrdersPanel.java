package me.stefan923.ordermanager.presentation.panels.order;

import me.stefan923.ordermanager.model.Order;
import me.stefan923.ordermanager.presentation.style.StyledJButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowOrdersPanel extends JPanel {

    private static final String[] TABLE_COLUMNS = new String[]{ "ID", "Client ID", "Product ID", "Quantity" };

    private final JButton addOrderButton = new StyledJButton("Add order").getButton();

    private final JButton backButton = new StyledJButton("Back").getButton();

    private final JTable ordersTable = new JTable();

    private List<Order> orders;

    public JPanel load() {
        this.setLayout(new BorderLayout());
        this.add(loadOrdersTable(), BorderLayout.CENTER);
        this.add(loadButtonsPanel(), BorderLayout.SOUTH);

        return this;
    }

    public void updateOrdersTable(List<Order> orders) {
        this.orders = orders;

        Object[][] ordersData = new Object[orders.size()][TABLE_COLUMNS.length];
        for (int i = 0; i < orders.size(); ++i) {
            Order order = orders.get(i);

            ordersData[i][0] = order.getId();
            ordersData[i][1] = order.getClientId();
            ordersData[i][2] = order.getProductId();
            ordersData[i][3] = order.getQuantity();
        }

        ordersTable.setModel(new DefaultTableModel(ordersData, TABLE_COLUMNS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ordersTable.setFillsViewportHeight(true);
    }

    public JTable getOrdersTable() {
        return ordersTable;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addAddOrderButtonListener(ActionListener actionListener) {
        addOrderButton.addActionListener(actionListener);
    }

    public void addBackButtonListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    private JPanel loadOrdersTable() {
        JScrollPane ordersTableScrollPane = new JScrollPane(ordersTable);
        ordersTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ordersTableScrollPane.setPreferredSize(new Dimension(540, 240));

        JPanel ordersTablePanel = new JPanel(new FlowLayout());
        ordersTablePanel.add(ordersTableScrollPane);

        return ordersTablePanel;
    }

    private JPanel loadButtonsPanel() {
        JPanel addOrderButtonPanel = new JPanel();
        addOrderButtonPanel.add(addOrderButton);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        JPanel centerButtonsPanel = new JPanel();
        centerButtonsPanel.add(addOrderButtonPanel);

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(centerButtonsPanel, BorderLayout.CENTER);
        buttonsPanel.add(backButtonPanel, BorderLayout.WEST);

        return buttonsPanel;
    }

}
