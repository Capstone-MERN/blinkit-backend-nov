package com.CapStone.BlinkIt_BackEnd.order.transformer;

import com.CapStone.BlinkIt_BackEnd.order.entity.OrderItem;
import com.CapStone.BlinkIt_BackEnd.order.model.OrderItemRequest;
import com.CapStone.BlinkIt_BackEnd.order.model.OrderItemResponse;

public class OrderItemTransformer {

    public static OrderItem OrderItemRequestToOrderItem(OrderItemRequest orderItemRequest){

        return OrderItem.builder()
                .quantity(orderItemRequest.getQuantity())
                .discount(orderItemRequest.getDiscount())
                .amountPaid(orderItemRequest.getAmountPaid())
                .build();
    }

    public static OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem){

        return OrderItemResponse.builder()
                .quantity(orderItem.getQuantity())
                .amountPaid(orderItem.getAmountPaid())
                .discount(orderItem.getDiscount())
                .order(orderItem.getOrder())
                //.product(orderItem.getProduct())
                .build();
    }
}
