package me.stefan923.warehousemanager.presentation.panels.product;

import me.stefan923.warehousemanager.model.Product;

public class EditProductPanel extends ProductPanel {

    private Product product;

    public void setProduct(Product product) {
        this.product = product;
        updateFields();
    }

    public Product getProduct() {
        return product;
    }

    private void updateFields() {
        nameTextField.setText(product.getName());
        priceTextField.setText(String.valueOf(product.getPrice()));
        stockTextField.setText(String.valueOf(product.getStock()));
    }

}
