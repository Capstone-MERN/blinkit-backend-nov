package com.CapStone.blinkitservice.cart.model;


import com.CapStone.blinkitservice.common.response.GenericResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCartResponse implements GenericResponse {

    List<UpdateCartProductResponse> products;

    Float totalWithoutDiscount;

    Float grandTotal;

    int uniqueQuantity;

    int totalQuantity;

}
