package com.CapStone.blinkitservice.order.transformer;

import com.CapStone.blinkitservice.order.entity.OrderItemEntity;
import com.CapStone.blinkitservice.order.model.OrderItemRequest;
import com.CapStone.blinkitservice.order.model.OrderItemResponse;

public class OrderItemTransformer {

    public static OrderItemEntity OrderItemRequestToOrderItem(OrderItemRequest orderItemRequest){

        return OrderItemEntity.builder()
                .quantity(orderItemRequest.getQuantity())
                .discount(orderItemRequest.getDiscount())
                .amountPaid(orderItemRequest.getAmountPaid())
                .build();
    }

    public static OrderItemResponse orderItemToOrderItemResponse(OrderItemEntity orderItem){

        return OrderItemResponse.builder()
                .quantity(orderItem.getQuantity())
                .amountPaid(orderItem.getAmountPaid())
                .discount(orderItem.getDiscount())
                .orderResponse(OrderTransformer.orderToOrderResponse(orderItem.getOrderEntity()))
//                .productResponse(ProductTransformer.productToProductResponse(orderItem.getProduct()))
                .build();
    }
}
