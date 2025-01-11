package com.CapStone.blinkitservice.product.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Integer productId;
    String title;
    Float price;
    String imageUrl;
    Integer maxQuantity;
    Integer quantity;
    String description;
    Float discountPercent;
    Float originalPrice;
}
