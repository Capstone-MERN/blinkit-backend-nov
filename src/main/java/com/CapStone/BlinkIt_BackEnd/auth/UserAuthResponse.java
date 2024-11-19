package com.CapStone.BlinkIt_BackEnd.auth;

import com.CapStone.BlinkIt_BackEnd.common.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthResponse {
    String id;
    //Role role;       ** role is removed currently
}
