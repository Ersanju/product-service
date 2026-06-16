package com.homekart.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateProductRequestDto {

    private String name;
    private String description;
    private BigDecimal price;

}
