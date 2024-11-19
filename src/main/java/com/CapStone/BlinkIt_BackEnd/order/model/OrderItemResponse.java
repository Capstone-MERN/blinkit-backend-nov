package com.CapStone.BlinkIt_BackEnd.order.model;

import com.CapStone.BlinkIt_BackEnd.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    Order order;

    //Product product

}
