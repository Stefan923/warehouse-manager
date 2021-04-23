package me.stefan923.ordermanager.presentation;

import me.stefan923.ordermanager.bll.AbstractBLL;
import me.stefan923.ordermanager.bll.ClientBLL;
import me.stefan923.ordermanager.bll.OrderBLL;
import me.stefan923.ordermanager.bll.ProductBLL;
import me.stefan923.ordermanager.model.Client;
import me.stefan923.ordermanager.model.Order;
import me.stefan923.ordermanager.model.Product;
import me.stefan923.ordermanager.presentation.panels.client.ClientPanel;
import me.stefan923.ordermanager.presentation.panels.client.CreateClientPanel;
import me.stefan923.ordermanager.presentation.panels.client.EditClientPanel;
import me.stefan923.ordermanager.presentation.panels.client.ShowClientsPanel;
import me.stefan923.ordermanager.presentation.panels.HomePagePanel;
import me.stefan923.ordermanager.presentation.panels.order.CreateOrderPanel;
import me.stefan923.ordermanager.presentation.panels.order.ShowOrdersPanel;
import me.stefan923.ordermanager.presentation.panels.product.CreateProductPanel;
import me.stefan923.ordermanager.presentation.panels.product.EditProductPanel;
import me.stefan923.ordermanager.presentation.panels.product.ProductPanel;
import me.stefan923.ordermanager.presentation.panels.product.ShowProductsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private final View view;

    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;

    private ShowClientsPanel showClientsPanel;
    private ShowProductsPanel showProductsPanel;
    private ShowOrdersPanel showOrdersPanel;

    public Controller(View view, ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.view = view;
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.orderBLL = orderBLL;
    }

    public void load() {
        showClientsPanel = (ShowClientsPanel) new ShowClientsPanel().load();
        showProductsPanel = (ShowProductsPanel) new ShowProductsPanel().load();
        showOrdersPanel = (ShowOrdersPanel) new ShowOrdersPanel().load();
        HomePagePanel homePagePanel = (HomePagePanel) new HomePagePanel().load();
        CreateClientPanel createClientPanel = (CreateClientPanel) new CreateClientPanel().load();
        EditClientPanel editClientPanel = (EditClientPanel) new EditClientPanel().load();
        CreateProductPanel createProductPanel = (CreateProductPanel) new CreateProductPanel().load();
        EditProductPanel editProductPanel = (EditProductPanel) new EditProductPanel().load();
        CreateOrderPanel createOrderPanel = (CreateOrderPanel) new CreateOrderPanel().load();

        showClientsPanel.addBackButtonListener(new PageButtonListener(homePagePanel));
        showClientsPanel.addAddClientButtonListener(new PageButtonListener(createClientPanel));
        showClientsPanel.addEditClientButtonListener(new PageButtonListener(editClientPanel));
        showClientsPanel.addDeleteClientButtonListener(new DeleteButtonListener());
        showClientsPanel.addSearchClientButtonListener(new SearchButtonListener());

        showProductsPanel.addBackButtonListener(new PageButtonListener(homePagePanel));
        showProductsPanel.addAddProductButtonListener(new PageButtonListener(createProductPanel));
        showProductsPanel.addEditProductButtonListener(new PageButtonListener(editProductPanel));
        showProductsPanel.addDeleteProductButtonListener(new DeleteButtonListener());

        showOrdersPanel.addBackButtonListener(new PageButtonListener(homePagePanel));
        showOrdersPanel.addAddOrderButtonListener(new PageButtonListener(createOrderPanel));

        createClientPanel.addConfirmButtonListener(new ConfirmButtonListener());
        createClientPanel.addCancelButtonListener(new PageButtonListener(showClientsPanel));

        editClientPanel.addConfirmButtonListener(new ConfirmButtonListener());
        editClientPanel.addCancelButtonListener(new PageButtonListener(showClientsPanel));

        createProductPanel.addConfirmButtonListener(new ConfirmButtonListener());
        createProductPanel.addCancelButtonListener(new PageButtonListener(showProductsPanel));

        editProductPanel.addConfirmButtonListener(new ConfirmButtonListener());
        editProductPanel.addCancelButtonListener(new PageButtonListener(showProductsPanel));

        createOrderPanel.addConfirmButtonListener(new ConfirmButtonListener());
        createOrderPanel.addCancelButtonListener(new PageButtonListener(showOrdersPanel));

        homePagePanel.addClientsButtonListener(new HomePageButtonListener(showClientsPanel, clientBLL));
        homePagePanel.addProductsButtonListener(new HomePageButtonListener(showProductsPanel, productBLL));
        homePagePanel.addOrdersButtonListener(new HomePageButtonListener(showOrdersPanel, orderBLL));
        view.setCurrentPanel(homePagePanel);
    }

    private class HomePageButtonListener implements ActionListener {

        private final JPanel panel;
        private final AbstractBLL<?> abstractBLL;

        public HomePageButtonListener(JPanel panel, AbstractBLL<?> abstractBLL) {
            this.panel = panel;
            this.abstractBLL = abstractBLL;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (panel instanceof ShowClientsPanel) {
                ShowClientsPanel showClientsPanel = (ShowClientsPanel) panel;
                showClientsPanel.load();
                showClientsPanel.updateClientsTable((((ClientBLL) abstractBLL).findAll()));
            } else if (panel instanceof ShowProductsPanel) {
                ShowProductsPanel showProductsPanel = (ShowProductsPanel) panel;
                showProductsPanel.load();
                showProductsPanel.updateProductsTable((((ProductBLL) abstractBLL).findAll()));
            } else if (panel instanceof ShowOrdersPanel) {
                ShowOrdersPanel showOrdersPanel = (ShowOrdersPanel) panel;
                showOrdersPanel.load();
                showOrdersPanel.updateOrdersTable((((OrderBLL) abstractBLL).findAll()));
            }
            view.setCurrentPanel(panel);
        }

    }

    private class PageButtonListener implements ActionListener {

        private final JPanel panel;

        public PageButtonListener(JPanel homePagePanel) {
            this.panel = homePagePanel;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (panel instanceof HomePagePanel) {
                view.setCurrentPanel(((HomePagePanel) panel).load());
            } else if (panel instanceof ShowClientsPanel) {
                view.setCurrentPanel(((ShowClientsPanel) panel).load());
            } else if (panel instanceof CreateClientPanel) {
                view.setCurrentPanel(((CreateClientPanel) panel).load());
            } else if (panel instanceof EditClientPanel) {
                ShowClientsPanel showClientsPanel = (ShowClientsPanel) view.getCurrentPanel();
                EditClientPanel editClientPanel = (EditClientPanel) panel;

                JTable clientsTable = showClientsPanel.getClientsTable();

                if (clientsTable.getSelectedRows().length != 1) {
                    view.sendError("Exactly one client must be selected.");
                } else {
                    int index = clientsTable.getSelectedRow();
                    editClientPanel.setClient(showClientsPanel.getClients().get(index));
                    view.setCurrentPanel((editClientPanel).load());
                }
                clientsTable.clearSelection();
            } else if (panel instanceof ShowProductsPanel) {
                view.setCurrentPanel(((ShowProductsPanel) panel).load());
            } else if (panel instanceof CreateProductPanel) {
                view.setCurrentPanel(((CreateProductPanel) panel).load());
            } else if (panel instanceof EditProductPanel) {
                ShowProductsPanel showProductsPanel = (ShowProductsPanel) view.getCurrentPanel();
                EditProductPanel editProductPanel = (EditProductPanel) panel;

                JTable productsTable = showProductsPanel.getProductsTable();

                if (productsTable.getSelectedRows().length != 1) {
                    view.sendError("Exactly one product must be selected.");
                } else {
                    int index = productsTable.getSelectedRow();
                    editProductPanel.setProduct(showProductsPanel.getProducts().get(index));
                    view.setCurrentPanel((editProductPanel).load());
                }
                productsTable.clearSelection();
            } else if (panel instanceof ShowOrdersPanel) {
                view.setCurrentPanel(((ShowOrdersPanel) panel).load());
            } else if (panel instanceof CreateOrderPanel) {
                CreateOrderPanel createOrderPanel = (CreateOrderPanel) panel;
                createOrderPanel.updateClients(clientBLL.findAll());
                createOrderPanel.updateProducts(productBLL.findAll());
                view.setCurrentPanel(createOrderPanel.load());
            }
        }

    }

    private class ConfirmButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel currentPanel = view.getCurrentPanel();
            if (currentPanel instanceof ClientPanel) {
                ClientPanel clientPanel = (ClientPanel) currentPanel;
                try {
                    Client client = new Client(
                            clientPanel.getNameTextField().getText(),
                            clientPanel.getAddressTextField().getText(),
                            clientPanel.getEmailTextField().getText(),
                            Integer.parseInt(clientPanel.getAgeTextField().getText())
                    );
                    if (clientPanel instanceof CreateClientPanel) {
                        clientBLL.insert(client);
                    } else if (clientPanel instanceof EditClientPanel) {
                        client.setId(((EditClientPanel) clientPanel).getClient().getId());
                        clientBLL.update(client);
                    }
                    showClientsPanel.updateClientsTable(clientBLL.findAll());
                    view.setCurrentPanel(showClientsPanel.load());
                    clientPanel.resetAllFields();
                } catch (NumberFormatException e) {
                    view.sendError("The age must be a number!");
                } catch (IllegalArgumentException e) {
                    view.sendError(e.getMessage());
                }
            } else if (currentPanel instanceof ProductPanel) {
                ProductPanel productPanel = (ProductPanel) currentPanel;
                try {
                    Product product = new Product(
                            productPanel.getNameTextField().getText(),
                            Double.parseDouble(productPanel.getPriceTextField().getText()),
                            Integer.parseInt(productPanel.getStockTextField().getText())
                    );
                    if (productPanel instanceof CreateProductPanel) {
                        productBLL.insert(product);
                    } else if (productPanel instanceof EditProductPanel) {
                        product.setId(((EditProductPanel) productPanel).getProduct().getId());
                        productBLL.update(product);
                    }
                    showProductsPanel.updateProductsTable(productBLL.findAll());
                    view.setCurrentPanel(showProductsPanel.load());
                    productPanel.resetAllFields();
                } catch (NumberFormatException e) {
                    view.sendError("The price and stock must be numbers!");
                } catch (IllegalArgumentException e) {
                    view.sendError(e.getMessage());
                }
            } else if (currentPanel instanceof CreateOrderPanel) {
                CreateOrderPanel createOrderPanel = (CreateOrderPanel) currentPanel;
                try {
                    int clientIndex = createOrderPanel.getClientComboBox().getSelectedIndex();
                    Client client = createOrderPanel.getClients().get(clientIndex);
                    int productIndex = createOrderPanel.getProductComboBox().getSelectedIndex();
                    Product product = createOrderPanel.getProducts().get(productIndex);
                    int quantity = Integer.parseInt(createOrderPanel.getQuantityTextField().getText());

                    if (quantity > product.getStock()) {
                        view.sendError("The stock is insufficient!");
                        return;
                    }

                    product.setStock(product.getStock() - quantity);
                    orderBLL.insert(new Order(client.getId(), product.getId(), quantity), client, product);
                    productBLL.update(product);

                    showOrdersPanel.updateOrdersTable(orderBLL.findAll());
                    view.setCurrentPanel(showOrdersPanel.load());
                    createOrderPanel.resetAllFields();
                } catch (NumberFormatException e) {
                    view.sendError("The quantity must be a number!");
                } catch (IllegalArgumentException e) {
                    view.sendError(e.getMessage());
                }
            }
        }

    }

    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel currentPanel = view.getCurrentPanel();
            if (currentPanel instanceof ShowClientsPanel) {
                ShowClientsPanel showClientsPanel = (ShowClientsPanel) view.getCurrentPanel();

                JTable clientsTable = showClientsPanel.getClientsTable();
                if (clientsTable.getSelectedRows().length != 1) {
                    view.sendError("Exactly one client must be selected.");
                } else {
                    int index = clientsTable.getSelectedRow();
                    clientBLL.delete(showClientsPanel.getClients().get(index).getId());
                    showClientsPanel.updateClientsTable(clientBLL.findAll());
                }
                clientsTable.clearSelection();
            } else if (currentPanel instanceof ShowProductsPanel) {
                ShowProductsPanel showProductsPanel = (ShowProductsPanel) view.getCurrentPanel();

                JTable productsTable = showProductsPanel.getProductsTable();
                if (productsTable.getSelectedRows().length != 1) {
                    view.sendError("Exactly one product must be selected.");
                } else {
                    int index = productsTable.getSelectedRow();
                    productBLL.delete(showProductsPanel.getProducts().get(index).getId());
                    showProductsPanel.updateProductsTable(productBLL.findAll());
                }
                productsTable.clearSelection();
            }
        }

    }

    private class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel currentPanel = view.getCurrentPanel();
            if (currentPanel instanceof ShowClientsPanel) {
                ShowClientsPanel showClientsPanel = (ShowClientsPanel) currentPanel;
                showClientsPanel.updateClientsTable(
                        clientBLL.findByName(showClientsPanel.getClientNameTextField().getText())
                );
            }
        }

    }

}
