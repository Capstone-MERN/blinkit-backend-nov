package com.CapStone.BlinkIt_BackEnd.order.model;

import com.CapStone.BlinkIt_BackEnd.order.Enum.DeliveryStatus;
import com.CapStone.BlinkIt_BackEnd.address.entity.AddressBook;
import com.CapStone.BlinkIt_BackEnd.user.entity.User;
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

    User user;

    AddressBook addressBook;
}
