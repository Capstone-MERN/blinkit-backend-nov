package com.CapStone.blinkitservice.common.error.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InvalidCartPayloadResponse extends Throwable {

    String message;

    public InvalidCartPayloadResponse(){
        this.message="Invalid Cart Payload";
    }

    public InvalidCartPayloadResponse(String message){
       this.message=message;
    }

}
