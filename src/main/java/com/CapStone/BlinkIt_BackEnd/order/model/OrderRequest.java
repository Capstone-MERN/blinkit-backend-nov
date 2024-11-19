package com.CapStone.BlinkIt_BackEnd.order.model;

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
