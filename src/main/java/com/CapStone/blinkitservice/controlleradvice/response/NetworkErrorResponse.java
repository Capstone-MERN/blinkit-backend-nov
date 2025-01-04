package com.CapStone.blinkitservice.controlleradvice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkErrorResponse {
    private String message;
    private Integer statusCode;
}
