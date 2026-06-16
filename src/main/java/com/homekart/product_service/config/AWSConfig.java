package com.homekart.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                        .region(Region.AP_SOUTH_1)
                        .build();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                    .region(Region.AP_SOUTH_1)
                    .build();
    }
    
}
