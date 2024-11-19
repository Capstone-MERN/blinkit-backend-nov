package com.CapStone.blinkitservice.cart.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDashboard {

    int estimatedTime;

    int quantity;

    Float grandTotal;


    Float deliveryCharge;

    Float handlingCharge;

    List<CartItemResponse> cartItemResponseList;


}
