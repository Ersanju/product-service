package com.homekart.product_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homekart.product_service.model.Product;
import com.homekart.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    public final ProductService productService;

    @PostMapping
    public String createProduct(@RequestBody Product product) {

        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {

        return productService.getProduct(id);
    }

}
