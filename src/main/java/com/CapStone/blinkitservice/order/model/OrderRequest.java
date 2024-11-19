package com.CapStone.blinkitservice.order.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    long timestamp;

    float orderedLocationLatitude;

    float orderedLocationLongitude;

    String contactNumber;
}
