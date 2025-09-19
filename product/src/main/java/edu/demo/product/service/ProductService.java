package edu.demo.product.service;

import edu.demo.product.domain.Product;
import edu.demo.product.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(String name, String description, double price, int stock) {
        return productRepository.save(Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build());
    }

    public Product updateProduct(Long id, int stock) {
        var p = productRepository.findById(id).orElseThrow();
        p.setStock(stock);
        return productRepository.save(p);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
