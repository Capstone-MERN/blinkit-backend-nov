package com.CapStone.blinkitservice.common.error;

import com.CapStone.blinkitservice.common.response.GenericResponse;
import lombok.Data;

@Data
public class GenericErrorResponse<T> implements GenericResponse {
    private T error;

    public GenericErrorResponse(T errorInstance) {
        this.error = errorInstance;
    }
}
