package com.CapStone.blinkitservice.user.dto.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {

    float latitude;

    float longitude;

    String addressLine1;

    String addressLine2;

    String addressLine3;

    String phone_no;
}
