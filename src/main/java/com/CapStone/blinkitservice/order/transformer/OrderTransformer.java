package com.CapStone.blinkitservice.order.transformer;
import com.CapStone.blinkitservice.order.entity.OrderEntity;
import com.CapStone.blinkitservice.order.model.OrderRequest;
import com.CapStone.blinkitservice.order.model.OrderResponse;
import com.CapStone.blinkitservice.user.transformer.UserTransformer;

public class OrderTransformer {

    public static OrderEntity orderRequestToOrder(OrderRequest orderRequest){
        return OrderEntity.builder()
                .timestamp(orderRequest.getTimestamp())
                .contactNumber(orderRequest.getContactNumber())
                .orderedLocationLatitude(orderRequest.getOrderedLocationLatitude())
                .orderedLocationLongitude(orderRequest.getOrderedLocationLongitude())
                .build();
    }

    public static OrderResponse orderToOrderResponse(OrderEntity order){
        return OrderResponse.builder()
                .amountSaved((order.getAmountSaved()))
                .totalAmountPaid(order.getTotalAmountPaid())
                .deliveryCharge(order.getDeliveryCharge())
                .timestamp(order.getTimestamp())
                .contactNumber(order.getContactNumber())
                .orderedLocationLatitude(order.getOrderedLocationLatitude())
                .orderedLocationLongitude(order.getOrderedLocationLongitude())
                .deliveryStatus(order.getDeliveryStatus())
                .userResponse(UserTransformer.userToUserResponse(order.getUserEntity()))
                .build();
    }
}
