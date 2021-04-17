package me.stefan923.ordermanager.businesslayer.validator;

import me.stefan923.ordermanager.model.Product;

public class ProductPriceValidator implements Validator<Product> {

    public static final double MINIMUM_PRICE = 0.0d;

    @Override
    public boolean validate(Product product) {
        return product != null && product.getPrice() >= MINIMUM_PRICE;
    }

}
