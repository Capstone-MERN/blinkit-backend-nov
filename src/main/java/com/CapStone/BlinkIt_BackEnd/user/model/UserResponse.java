package com.CapStone.BlinkIt_BackEnd.user.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {

    String name;

    String email;

    String mobileNumber;
}
