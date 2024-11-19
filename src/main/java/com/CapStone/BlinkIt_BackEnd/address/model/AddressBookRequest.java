package com.CapStone.BlinkIt_BackEnd.address.model;

import com.CapStone.BlinkIt_BackEnd.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
