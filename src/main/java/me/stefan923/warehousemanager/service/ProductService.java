package me.stefan923.warehousemanager.service;

import me.stefan923.warehousemanager.service.validator.ProductPriceValidator;
import me.stefan923.warehousemanager.service.validator.ProductStockValidator;
import me.stefan923.warehousemanager.repository.ProductRepository;
import me.stefan923.warehousemanager.model.Product;

import java.util.List;

public class ProductService extends Service<Product> {

    public ProductService() {
        super(List.of(new ProductPriceValidator(), new ProductStockValidator()), new ProductRepository());
    }

}
