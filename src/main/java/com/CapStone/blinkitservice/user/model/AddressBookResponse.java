package com.CapStone.blinkitservice.user.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressBookResponse {

    float latitude;

    float longitude;

    String addressLine1;

    String addressLine2;

    String addressLine3;

    UserResponse userResponse;

}
