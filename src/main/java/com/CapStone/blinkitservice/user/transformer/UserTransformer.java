package com.CapStone.blinkitservice.user.transformer;

import com.CapStone.blinkitservice.user.entity.UserEntity;
import com.CapStone.blinkitservice.user.model.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTransformer {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static UserEntity userRequestToUser(com.CapStone.blinkitservice.user.model.UserRequest userRequest){
        return UserEntity.builder()
                .email(userRequest.getEmail())
                .mobileNumber(userRequest.getMobileNumber())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .build();
    }

    public static UserResponse userToUserResponse(UserEntity user){
        return UserResponse.builder()
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .name(user.getName())
                .build();
    }
}
