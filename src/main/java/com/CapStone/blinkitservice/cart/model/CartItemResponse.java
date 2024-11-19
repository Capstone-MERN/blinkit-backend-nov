package com.CapStone.blinkitservice.cart.model;

import com.CapStone.blinkitservice.user.model.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {

    Integer quantity;

    UserResponse userResponse;

//    ProductResponse productResponse;
}
