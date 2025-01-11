package com.CapStone.blinkitservice.auth;

import com.CapStone.blinkitservice.auth.model.SigninRequest;
import com.CapStone.blinkitservice.auth.model.SignupResponse;
import com.CapStone.blinkitservice.configuration.jwt.JwtManager;
import com.CapStone.blinkitservice.controlleradvice.exceptions.BadRequestException;
import com.CapStone.blinkitservice.controlleradvice.exceptions.InternalServerException;
import com.CapStone.blinkitservice.user.UserConstraints;
import com.CapStone.blinkitservice.user.repository.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import com.CapStone.blinkitservice.auth.model.SignupRequest;
import org.springframework.dao.DataIntegrityViolationException;
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

    public String authenticate(SigninRequest signinRequest) {
        UserEntity user = userRepository.findByEmail(signinRequest.getEmail());
        if (user != null && passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            return jwtManager.generateToken(user.getEmail());
        }
        throw new BadRequestException("Invalid credentials");
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        UserEntity user = UserEntity.builder()
                .email(signupRequest.getEmail())
                .mobileNumber(signupRequest.getMobileNumber())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .name(signupRequest.getName())
                .build();

        try {
            userRepository.save(user);
        }
        catch(DataIntegrityViolationException exception) {
            String duplicateField = "";

            if (exception.getMessage().contains(UserConstraints.UNIQUE_EMAIL)) {
                duplicateField += "email already exist. ";
            } else if (exception.getMessage().contains(UserConstraints.UNIQUE_MOBILE_NUMBER)) {
                duplicateField += "mobile number already exist.";
            } else {
                duplicateField += "details already exist.";
            }
            throw new BadRequestException(duplicateField);
        }
        catch(Exception exception){
            throw new InternalServerException(exception.getMessage());
        }

        return SignupResponse.builder()
                    .message("Signup success")
                    .build();
    }
}
