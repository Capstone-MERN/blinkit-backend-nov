package com.CapStone.blinkitservice.order.orderDTO;

import com.CapStone.blinkitservice.common.response.GenericResponse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderResponse implements GenericResponse {

    int orderId;

    String message;
}
