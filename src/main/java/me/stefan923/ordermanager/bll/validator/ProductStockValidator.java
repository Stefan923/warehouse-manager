package me.stefan923.ordermanager.bll.validator;

import me.stefan923.ordermanager.model.Product;

public class ProductStockValidator implements Validator<Product> {

    public static final int MINIMUM_STOCK = 0;

    @Override
    public boolean validate(Product product) {
        if (product != null && product.getStock() >= MINIMUM_STOCK) {
            return true;
        }

        throw new IllegalArgumentException("This is not a valid stock!");
    }

}
