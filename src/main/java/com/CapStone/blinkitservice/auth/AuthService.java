package com.CapStone.blinkitservice.auth;

import com.CapStone.blinkitservice.configuration.jwt.JwtManager;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import com.CapStone.blinkitservice.user.model.UserRequest;
import com.CapStone.blinkitservice.user.model.UserResponse;
import com.CapStone.blinkitservice.user.transformer.UserTransformer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthService {

    @Autowired
    JwtManager jwtManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public String authenticate(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtManager.generateToken(user.getEmail());
            return token;
        }

        return null;
    }

    public UserResponse signup(UserRequest userRequest) {
        UserEntity user = UserTransformer.userRequestToUser(userRequest);
        UserEntity savedUser = userRepository.save(user);
        return UserTransformer.userToUserResponse(savedUser);
    }
}
