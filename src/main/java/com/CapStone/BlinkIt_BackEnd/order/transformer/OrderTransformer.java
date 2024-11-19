package com.CapStone.BlinkIt_BackEnd.order.transformer;

import com.CapStone.BlinkIt_BackEnd.order.entity.Order;
import com.CapStone.BlinkIt_BackEnd.order.model.OrderRequest;
import com.CapStone.BlinkIt_BackEnd.order.model.OrderResponse;

public class OrderTransformer {

    public static Order orderRequestToOrder(OrderRequest orderRequest){
        return Order.builder()
                .timestamp(orderRequest.getTimestamp())
                .contactNumber(orderRequest.getContactNumber())
                .orderedLocationLatitude(orderRequest.getOrderedLocationLatitude())
                .orderedLocationLongitude(orderRequest.getOrderedLocationLongitude())
                .build();
    }

    public static OrderResponse orderToOrderResponse(Order order){
        return OrderResponse.builder()
                .amountSaved(order.getAmountSaved())
                .totalAmountPaid(order.getTotalAmountPaid())
                .deliveryCharge(order.getDeliveryCharge())
                .timestamp(order.getTimestamp())
                .contactNumber(order.getContactNumber())
                .orderedLocationLatitude(order.getOrderedLocationLatitude())
                .orderedLocationLongitude(order.getOrderedLocationLongitude())
                .deliveryStatus(order.getDeliveryStatus())
                .user(order.getUser())
                .addressBook(order.getAddressBook())
                .build();
    }
}
