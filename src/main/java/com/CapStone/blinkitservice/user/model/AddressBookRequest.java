package com.CapStone.blinkitservice.user.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressBookRequest {

    float latitude;

    float longitude;

    String addressLine1;

    String addressLine2;

    String addressLine3;

}
