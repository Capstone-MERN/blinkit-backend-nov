package com.CapStone.blinkitservice.order.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {

    int quantity;

    Float amountPaid;

    Float discount;

}
