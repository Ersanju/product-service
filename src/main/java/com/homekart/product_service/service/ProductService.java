package com.homekart.product_service.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.homekart.product_service.exception.ProductNotFoundException;
import com.homekart.product_service.model.Product;
import com.homekart.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public final S3Service s3Service;

    public String createProduct(Product product, MultipartFile image) throws IOException {

        log.info("Creating product: {}", product.getName());
        String imgUrl = s3Service.uploadFile(image);

        product.setProductId(UUID.randomUUID().toString());
        product.setImageUrl(imgUrl);

        return productRepository.saveProduct(product);
    }

    public Product getProduct(String productId) {

        log.info("Fetching product with Id: {}", productId);

        Product product = productRepository.getProductById(productId);

        if (product == null) {
            throw new ProductNotFoundException(
                    "Product not found with ID: " + productId);
        }

        return product;
    }

}
