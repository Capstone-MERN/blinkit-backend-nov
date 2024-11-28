package com.CapStone.blinkitservice.cart.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCartResponse {

    List<UpdateCartProductResponse> products;

    Float totalWithoutDiscount;

    Float handlingCharges;

    Float deliveryCharges;

    Float grandTotal;

    int uniqueQuantity;

    int totalQuantity;

}
