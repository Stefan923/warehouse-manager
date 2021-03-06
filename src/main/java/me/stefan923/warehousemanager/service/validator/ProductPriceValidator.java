package me.stefan923.warehousemanager.service.validator;

import me.stefan923.warehousemanager.model.Product;

public class ProductPriceValidator implements Validator<Product> {

    public static final double MINIMUM_PRICE = 0.0d;

    @Override
    public boolean validate(Product product) {
        if (product != null && product.getPrice() >= MINIMUM_PRICE) {
            return true;
        }

        throw new IllegalArgumentException("This is not a valid price!");
    }

}
