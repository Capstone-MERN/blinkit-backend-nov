package com.CapStone.BlinkIt_BackEnd.cart.model;

import com.CapStone.BlinkIt_BackEnd.user.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {

    Integer quantity;

    User user;

//  Product product
}
