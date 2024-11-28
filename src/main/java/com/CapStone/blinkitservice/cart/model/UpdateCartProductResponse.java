package com.CapStone.blinkitservice.cart.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCartProductResponse {

    int productId;

    String name;

    String imageUrl;

    Double originalPrice;

    Double discountedPrice;

    int maxOrderLimit;

    String description;

    String unit;

    int quantity;

    boolean isAvailable;

}
