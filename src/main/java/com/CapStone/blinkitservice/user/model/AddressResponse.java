package com.CapStone.blinkitservice.user.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    Integer id;
    float latitude;
    float longitude;
    String addressLine1;
    String addressLine2;
    String addressLine3;

}
