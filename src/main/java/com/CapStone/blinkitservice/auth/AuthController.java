package com.CapStone.blinkitservice.auth;

import com.CapStone.blinkitservice.user.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody AuthRequest authRequest) {

        String response = authService.authenticate(authRequest);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok("Bearer " + response);
    }



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserRequest userRequest) {

        if(userRequest.getMobileNumber().length() != 10)
            return new ResponseEntity<>(AuthResponse.builder().message("Invalid mobile number format").build(), HttpStatus.BAD_REQUEST);

        try {
            AuthResponse response = authService.signup(userRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return new ResponseEntity<>(AuthResponse.builder().message("Entered email or mobile number already exists").build(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(AuthResponse.builder()
                    .message("Sorry, sign up failed")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}