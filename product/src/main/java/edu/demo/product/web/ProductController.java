package edu.demo.product.web;


import edu.demo.product.domain.Product;
import edu.demo.product.service.ProductService;
import edu.demo.product.web.dto.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> all() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ProductRequest req) {
        Product product = productService.createProduct(req.name(), req.description(), req.price(), req.stock());
        return ResponseEntity.created(URI.create("/api/products/" + product.getId())).build();
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody ProductRequest req) {
        return ResponseEntity.ok(productService.updateProduct(req.id(), req.stock()));
    }
}
