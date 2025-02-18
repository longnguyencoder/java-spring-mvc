package com.example.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.laptopshop.domain.Product;
import com.example.laptopshop.domain.Role;
import com.example.laptopshop.domain.User;
import com.example.laptopshop.repository.ProductRepository;
import com.example.laptopshop.repository.RoleRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> fetchProduct() {
        return this.productRepository.findAll();
    }
}
