package com.homekart.product_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		System.out.println("Application running...");
	}

	@Bean
	CommandLineRunner tests3(S3Client s3Client) {
		return args -> {
			System.out.println("Fetching s3 buckets...");
			s3Client.listBuckets(ListBucketsRequest.builder().build())
					.buckets()
					.forEach(bucket -> System.out.println(bucket.name()));

		};
	}

}
