package com.CapStone.blinkitservice.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchResponseDto {

    int pageNumber;
    int size;
    List<ProductResponseDto> products;
}
