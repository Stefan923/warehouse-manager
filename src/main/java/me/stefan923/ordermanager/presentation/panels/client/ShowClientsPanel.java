package me.stefan923.ordermanager.presentation.panels.client;

import me.stefan923.ordermanager.model.Client;
import me.stefan923.ordermanager.presentation.style.StyledJButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowClientsPanel extends JPanel {

    private static final String[] TABLE_COLUMNS = new String[]{ "Name", "Address", "Email", "Age" };

    private final JButton addClientButton = new StyledJButton("Add client").getButton();
    private final JButton editClientButton = new StyledJButton("Edit client").getButton();
    private final JButton deleteClientButton = new StyledJButton("Delete client").getButton();
    private final JButton searchClientButton = new StyledJButton("Search client").getButton();

    private final JButton backButton = new StyledJButton("Back").getButton();

    private final TextField clientNameTextField = new TextField(15);

    private final JTable clientsTable = new JTable();

    private List<Client> clients;

    public JPanel load() {
        this.setLayout(new BorderLayout());
        this.add(loadSearchPanel(), BorderLayout.NORTH);
        this.add(loadClientsTable(), BorderLayout.CENTER);
        this.add(loadButtonsPanel(), BorderLayout.SOUTH);

        return this;
    }

    public void updateClientsTable(List<Client> clients) {
        this.clients = clients;

        Object[][] clientsData = new Object[clients.size()][TABLE_COLUMNS.length];
        for (int i = 0; i < clients.size(); ++i) {
            Client client = clients.get(i);

            clientsData[i][0] = client.getName();
            clientsData[i][1] = client.getAddress();
            clientsData[i][2] = client.getEmail();
            clientsData[i][3] = client.getAge();
        }

        clientsTable.setModel(new DefaultTableModel(clientsData, TABLE_COLUMNS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        clientsTable.setFillsViewportHeight(true);
    }

    public JTable getClientsTable() {
        return clientsTable;
    }

    public List<Client> getClients() {
        return clients;
    }

    public TextField getClientNameTextField() {
        return clientNameTextField;
    }

    public void addAddClientButtonListener(ActionListener actionListener) {
        addClientButton.addActionListener(actionListener);
    }

    public void addEditClientButtonListener(ActionListener actionListener) {
        editClientButton.addActionListener(actionListener);
    }

    public void addDeleteClientButtonListener(ActionListener actionListener) {
        deleteClientButton.addActionListener(actionListener);
    }

    public void addBackButtonListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public void addSearchClientButtonListener(ActionListener actionListener) {
        searchClientButton.addActionListener(actionListener);
    }

    private JPanel loadSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Client name:"));
        searchPanel.add(clientNameTextField);
        searchPanel.add(searchClientButton);

        return searchPanel;
    }

    private JPanel loadClientsTable() {
        JScrollPane clientsTableScrollPane = new JScrollPane(clientsTable);
        clientsTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        clientsTableScrollPane.setPreferredSize(new Dimension(540, 240));

        JPanel clientsTablePanel = new JPanel(new FlowLayout());
        clientsTablePanel.add(clientsTableScrollPane);

        return clientsTablePanel;
    }

    private JPanel loadButtonsPanel() {
        JPanel addClientButtonPanel = new JPanel();
        addClientButtonPanel.add(addClientButton);

        JPanel editClientButtonPanel = new JPanel();
        editClientButtonPanel.add(editClientButton);

        JPanel deleteClientButtonPanel = new JPanel();
        deleteClientButtonPanel.add(deleteClientButton);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        JPanel centerButtonsPanel = new JPanel();
        centerButtonsPanel.add(addClientButtonPanel);
        centerButtonsPanel.add(editClientButtonPanel);
        centerButtonsPanel.add(deleteClientButtonPanel);

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(centerButtonsPanel, BorderLayout.CENTER);
        buttonsPanel.add(backButtonPanel, BorderLayout.WEST);

        return buttonsPanel;
    }

}
