package com.CapStone.blinkitservice.controlleradvice;

import com.CapStone.blinkitservice.controlleradvice.exceptions.BadRequestException;
import com.CapStone.blinkitservice.controlleradvice.exceptions.InternalServerException;
import com.CapStone.blinkitservice.controlleradvice.response.NetworkErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<NetworkErrorResponse> handleBadRequest(BadRequestException exception){
        NetworkErrorResponse response = NetworkErrorResponse.builder()
                .message(exception.getMessage())
                .statusCode(exception.getStatusCode())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<NetworkErrorResponse> handleInternalServerError(InternalServerException exception){
        NetworkErrorResponse response = NetworkErrorResponse.builder()
                .message(exception.getMessage())
                .statusCode(exception.getStatusCode())
                .build();
        return ResponseEntity.internalServerError().body(response);
    }
}
