package com.CapStone.blinkitservice.controlleradvice.exceptions;

import lombok.Getter;

@Getter
public class NetworkException extends RuntimeException {
    protected String message;
    protected Integer statusCode;

    public NetworkException(String message, Integer statusCode){
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
