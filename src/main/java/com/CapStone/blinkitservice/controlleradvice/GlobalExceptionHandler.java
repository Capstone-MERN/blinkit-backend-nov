package com.CapStone.blinkitservice.controlleradvice;

import com.CapStone.blinkitservice.common.error.GenericErrorResponse;
import com.CapStone.blinkitservice.common.response.GenericResponse;
import com.CapStone.blinkitservice.controlleradvice.exceptions.BadRequestException;
import com.CapStone.blinkitservice.controlleradvice.exceptions.InternalServerException;
import com.CapStone.blinkitservice.controlleradvice.response.NetworkErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new GenericErrorResponse<>(errorMessages));
    }
}
