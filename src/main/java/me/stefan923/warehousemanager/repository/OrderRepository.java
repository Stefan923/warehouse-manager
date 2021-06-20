package me.stefan923.warehousemanager.repository;

import me.stefan923.warehousemanager.model.Order;

public class OrderRepository extends Repository<Order> {

    public OrderRepository() {
        super(Order.class);
    }

}
