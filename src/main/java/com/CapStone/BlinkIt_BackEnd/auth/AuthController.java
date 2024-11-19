package com.CapStone.BlinkIt_BackEnd.auth;

import com.CapStone.BlinkIt_BackEnd.user.model.UserRequest;
import com.CapStone.BlinkIt_BackEnd.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String mobileNumber, @RequestParam String password) {

        String response = authService.authenticate(mobileNumber, password);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok("Bearer " + response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {

        try{
            UserResponse response = authService.signup(userRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

