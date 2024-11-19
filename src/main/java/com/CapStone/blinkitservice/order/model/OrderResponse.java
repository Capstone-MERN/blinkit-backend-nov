package com.CapStone.blinkitservice.order.model;

import com.CapStone.blinkitservice.order.enums.DeliveryStatus;
import com.CapStone.blinkitservice.user.model.AddressBookResponse;
import com.CapStone.blinkitservice.user.model.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    long timestamp;

    Float totalAmountPaid;

    Float deliveryCharge;

    Float amountSaved;

    float orderedLocationLatitude;

    float orderedLocationLongitude;

    String contactNumber;

    DeliveryStatus deliveryStatus;

    UserResponse userResponse;

    AddressBookResponse addressBookResponse;
}
