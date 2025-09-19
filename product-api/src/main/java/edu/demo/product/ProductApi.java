package edu.demo.product;

public interface ProductApi {
    Product getProductById(Long productId);

    void updateStockFor(Product p);
}
