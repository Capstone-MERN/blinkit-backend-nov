package com.CapStone.blinkitservice.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressRequest {
    float latitude;

    Integer addressId;

    float longitude;

    String addressLine1;

    String addressLine2;

    String addressLine3;

    String phoneNo;
}
