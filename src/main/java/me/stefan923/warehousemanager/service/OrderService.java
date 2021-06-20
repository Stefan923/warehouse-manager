package me.stefan923.warehousemanager.service;

import me.stefan923.warehousemanager.service.validator.OrderQuantityValidator;
import me.stefan923.warehousemanager.repository.OrderRepository;
import me.stefan923.warehousemanager.model.Client;
import me.stefan923.warehousemanager.model.Order;
import me.stefan923.warehousemanager.model.Product;

import java.util.Collections;

public class OrderService extends Service<Order> {

    private final ReceiptFactory receiptFactory = ReceiptFactory.getInstance();

    public OrderService() {
        super(Collections.singletonList(new OrderQuantityValidator()), new OrderRepository());
    }

    public Order insert(Order order, Client client, Product product) {
        final Order finalOrder = super.insert(order);

        receiptFactory.createReceipt(order, client, product);

        return finalOrder;
    }

}
