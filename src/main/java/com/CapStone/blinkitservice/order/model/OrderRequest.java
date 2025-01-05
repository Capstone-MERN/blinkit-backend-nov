package com.CapStone.blinkitservice.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrderRequest {

    Integer addressId;

    long timestamp;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must contain only digits, and must be exactly 10 digits")
    String contactNumber;

}
