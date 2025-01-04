package com.CapStone.blinkitservice.controlleradvice.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerException extends NetworkException {
    public InternalServerException(String message){
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
