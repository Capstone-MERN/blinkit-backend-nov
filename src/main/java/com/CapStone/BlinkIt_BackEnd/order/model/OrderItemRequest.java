package com.CapStone.BlinkIt_BackEnd.order.model;

import jakarta.persistence.Column;
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
