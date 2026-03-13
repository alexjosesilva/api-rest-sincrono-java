package com.example.api.service;

import com.example.api.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ProductService() {
        products.add(new Product(idCounter.getAndIncrement(), "Laptop", 2999.99, "High-performance laptop"));
        products.add(new Product(idCounter.getAndIncrement(), "Mouse", 49.99, "Wireless ergonomic mouse"));
        products.add(new Product(idCounter.getAndIncrement(), "Keyboard", 129.99, "Mechanical keyboard"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product create(Product product) {
        product.setId(idCounter.getAndIncrement());
        products.add(product);
        return product;
    }

    public Optional<Product> update(Long id, Product updated) {
        return findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setDescription(updated.getDescription());
            return existing;
        });
    }

    public boolean delete(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
