package me.stefan923.ordermanager.dao;

import me.stefan923.ordermanager.model.Order;

public class OrderDAO extends AbstractDAO<Order> {

    public OrderDAO() {
        super(Order.class);
    }

}
