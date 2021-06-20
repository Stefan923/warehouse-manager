package me.stefan923.warehousemanager.repository;

import me.stefan923.warehousemanager.model.Product;

public class ProductRepository extends Repository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

}
