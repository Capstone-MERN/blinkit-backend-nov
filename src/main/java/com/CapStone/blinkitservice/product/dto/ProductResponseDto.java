package com.CapStone.blinkitservice.product.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

    String title;
    Double price;
    String imageUrl;
    Integer maxQuantity;
    Integer quantity;
    String description;
    Double discountPercent;
    Double orginalPrice;
}
