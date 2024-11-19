package com.CapStone.BlinkIt_BackEnd.auth;

import com.CapStone.BlinkIt_BackEnd.configuration.jwt.JwtManager;
import com.CapStone.BlinkIt_BackEnd.user.entity.User;
import com.CapStone.BlinkIt_BackEnd.user.UserRepository;
import com.CapStone.BlinkIt_BackEnd.user.model.UserRequest;
import com.CapStone.BlinkIt_BackEnd.user.model.UserResponse;
import com.CapStone.BlinkIt_BackEnd.user.transformer.UserTransformer;
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

    public String authenticate(String mobileNumber, String password) {
        User user = userRepository.findByMobileNumber(mobileNumber);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtManager.generateToken(user.getMobileNumber());
            return token;
        }

        return null;
    }

    public UserResponse signup(UserRequest userRequest) {
        User user = UserTransformer.userRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserTransformer.userToUserResponse(savedUser);
    }
}
