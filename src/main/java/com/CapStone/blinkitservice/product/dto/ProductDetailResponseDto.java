package com.CapStone.blinkitservice.product.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProductDetailResponseDto {

    int id;
    String title;
    String description;
    Map<String, Object> gallery;
    int cartQuantity;
    int maxQuantityLimit;
    Map<String, Object> productDetails;

}


