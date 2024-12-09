package com.CapStone.blinkitservice.common.error.exception;

public class InvalidCartPayloadResponse extends RuntimeException {

    public InvalidCartPayloadResponse() {
        super("Invalid Cart Payload");
    }

    public InvalidCartPayloadResponse(String message) {
        super(message);
    }

}
