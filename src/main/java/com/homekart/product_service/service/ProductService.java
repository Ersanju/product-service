package com.homekart.product_service.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.homekart.product_service.model.Product;
import com.homekart.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public final S3Service s3Service;

    public String createProduct(Product product, MultipartFile image) throws IOException {

        String imgUrl = s3Service.uploadFile(image);

        product.setProductId(UUID.randomUUID().toString());
        product.setImageUrl(imgUrl);

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
