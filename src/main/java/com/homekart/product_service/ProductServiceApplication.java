package com.homekart.product_service;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.homekart.product_service.model.Product;
import com.homekart.product_service.repository.ProductRepository;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        System.out.println("Application running...");
    }

    @Bean
    CommandLineRunner testRepository(ProductRepository repository) {
        return args -> {

            Product product = Product.builder()
                    .productId("P1001")
                    .name("iPhone 16")
                    .description("Apple Smartphone")
                    .price(new BigDecimal("79999"))
                    .imageUrl("iphone16.jpg")
                    .build();

            String response = repository.saveProduct(product);

            System.out.println(response);

            Product savedProduct = repository.getProductById("P1001");

            System.out.println(savedProduct);
        };
    }

}
