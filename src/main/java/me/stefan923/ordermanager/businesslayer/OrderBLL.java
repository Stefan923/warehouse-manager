package me.stefan923.ordermanager.businesslayer;

import me.stefan923.ordermanager.businesslayer.validator.OrderQuantityValidator;
import me.stefan923.ordermanager.dao.OrderDAO;
import me.stefan923.ordermanager.model.Client;
import me.stefan923.ordermanager.model.Order;
import me.stefan923.ordermanager.model.Product;

import java.util.Collections;

public class OrderBLL extends AbstractBLL<Order> {

    private final ReceiptFactory receiptFactory = ReceiptFactory.getInstance();

    public OrderBLL() {
        super(Collections.singletonList(new OrderQuantityValidator()), new OrderDAO());
    }

    public Order insert(Order order, Client client, Product product) {
        final Order finalOrder = super.insert(order);

        receiptFactory.createReceipt(order, client, product);

        return finalOrder;
    }

}
