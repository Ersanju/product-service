package com.homekart.product_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.homekart.product_service.model.Product;
import com.homekart.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public String createProduct(Product product) {

        product.setProductId(UUID.randomUUID().toString());

        return productRepository.saveProduct(product);
    }

    public Product getProduct(String productId) {

        if (productId == null || productId.isBlank()) {
            throw new RuntimeException("Product ID is required");
        }

        Product product = productRepository.getProductById(productId);

        if (product == null) {
            throw new RuntimeException(
                    "Product not found with ID: " + productId);
        }

        return product;
    }

}
