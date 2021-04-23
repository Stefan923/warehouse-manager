package me.stefan923.ordermanager.bll;

import me.stefan923.ordermanager.bll.validator.ProductPriceValidator;
import me.stefan923.ordermanager.bll.validator.ProductStockValidator;
import me.stefan923.ordermanager.dao.ProductDAO;
import me.stefan923.ordermanager.model.Product;

import java.util.List;

public class ProductBLL extends AbstractBLL<Product> {

    public ProductBLL() {
        super(List.of(new ProductPriceValidator(), new ProductStockValidator()), new ProductDAO());
    }

}
