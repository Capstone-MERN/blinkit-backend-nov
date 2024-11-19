package com.CapStone.BlinkIt_BackEnd.user.transformer;

import com.CapStone.BlinkIt_BackEnd.user.entity.User;
import com.CapStone.BlinkIt_BackEnd.user.model.UserRequest;
import com.CapStone.BlinkIt_BackEnd.user.model.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTransformer {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User userRequestToUser(UserRequest userRequest){
        return User.builder()
                .email(userRequest.getEmail())
                .mobileNumber(userRequest.getMobileNumber())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .build();
    }

    public static UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .name(user.getName())
                .build();
    }
}
