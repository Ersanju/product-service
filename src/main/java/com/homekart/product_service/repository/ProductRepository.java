package com.homekart.product_service.repository;

import org.springframework.stereotype.Repository;

import com.homekart.product_service.model.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductRepository {

        private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

        private static final String TABLE_NAME = "products";

        public String saveProduct(Product product) {

                log.info("Saving product {} to DynamoDB", product.getName());

                DynamoDbTable<Product> productTable = dynamoDbEnhancedClient.table(
                                TABLE_NAME,
                                TableSchema.fromBean(Product.class));

                productTable.putItem(product);

                return "Product saved successfully with ID: "
                                + product.getProductId();
        }

        public Product getProductById(String productId) {

                log.info("Fetching product from DynamoDB with Id: {}", productId);

                DynamoDbTable<Product> productTable = dynamoDbEnhancedClient.table(
                                TABLE_NAME,
                                TableSchema.fromBean(Product.class));

                return productTable.getItem(
                                Key.builder()
                                                .partitionValue(productId)
                                                .build());

        }
}