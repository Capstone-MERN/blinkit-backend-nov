package com.CapStone.blinkitservice.order.model;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderItemResponse {

    int quantity;

    Float amountPaid;

    Float discount;

   OrderResponse orderResponse;

//   ProductResponse productResponse;

}
