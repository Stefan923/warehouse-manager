package me.stefan923.warehousemanager.service.validator;

import me.stefan923.warehousemanager.model.Order;

public class OrderQuantityValidator implements Validator<Order> {

    public static final int MINIMUM_QUANTITY = 0;

    @Override
    public boolean validate(Order order) {
        if (order != null && order.getQuantity() >= MINIMUM_QUANTITY) {
            return true;
        }

        throw new IllegalArgumentException("This is not a valid quantity!");
    }

}
