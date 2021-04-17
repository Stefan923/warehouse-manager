package me.stefan923.ordermanager.businesslayer;

import me.stefan923.ordermanager.dao.OrderDAO;
import me.stefan923.ordermanager.model.Order;

import java.util.Collections;

public class OrderBLL extends AbstractBLL<Order> {

    public OrderBLL() {
        super(Collections.emptyList(), new OrderDAO());
    }

}
