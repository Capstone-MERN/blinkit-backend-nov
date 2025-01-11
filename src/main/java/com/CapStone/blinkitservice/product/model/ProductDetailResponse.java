package com.CapStone.blinkitservice.product.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    int id;
    String title;
    String description;
    List<String> gallery;
    int cartQuantity;
    int maxQuantityLimit;
    List<Map<String, String>> productDetails;
}


