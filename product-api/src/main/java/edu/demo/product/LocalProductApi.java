package edu.demo.product;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LocalProductApi implements ProductApi {
    private final RestClient restClient;

    public LocalProductApi() {
        this.restClient = RestClient.create();
    }

    @Override
    public Product getProductById(Long productId) {
        return restClient.get().uri("http://localhost:8084/api/products/{id}", productId)
                .retrieve()
                .body(Product.class);
    }

    @Override
    public void updateStockFor(Product p) {
        restClient.put().uri("http://localhost:8084/api/products").body(p).retrieve().body(Product.class);
    }
}
