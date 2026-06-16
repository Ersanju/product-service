package com.homekart.product_service.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homekart.dto.CreateProductRequestDto;
import com.homekart.product_service.model.Product;
import com.homekart.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    public final ProductService productService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String createProduct(
            @RequestPart("product") String productJson,
            @RequestPart("image") MultipartFile image) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CreateProductRequestDto requestDto = mapper.readValue(productJson, CreateProductRequestDto.class);

        Product product = Product.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .build();

        return productService.createProduct(product, image);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {

        return productService.getProduct(id);
    }

}
