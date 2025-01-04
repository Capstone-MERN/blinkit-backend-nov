package com.CapStone.blinkitservice.controlleradvice.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends NetworkException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
