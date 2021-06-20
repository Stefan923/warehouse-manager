package me.stefan923.warehousemanager;

import me.stefan923.warehousemanager.service.ClientService;
import me.stefan923.warehousemanager.service.OrderService;
import me.stefan923.warehousemanager.service.ProductService;
import me.stefan923.warehousemanager.presentation.Controller;
import me.stefan923.warehousemanager.presentation.View;

public class WarehouseManager {

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view, new ClientService(), new ProductService(), new OrderService());

        controller.load();
        view.load();
        view.setVisible(true);
    }

}
