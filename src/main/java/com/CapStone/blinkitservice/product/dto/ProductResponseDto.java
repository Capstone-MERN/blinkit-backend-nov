package com.CapStone.blinkitservice.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

    String title;
    Float price;
    String imageUrl;
    Integer maxQuantity;
    Integer quantity;
    String description;
    Float discountPercent;
    Float originalPrice;
}
