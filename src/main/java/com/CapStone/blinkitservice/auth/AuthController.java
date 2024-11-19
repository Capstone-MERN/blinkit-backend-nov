package com.CapStone.blinkitservice.auth;

import com.CapStone.blinkitservice.user.model.UserRequest;
import com.CapStone.blinkitservice.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {

        String response = authService.authenticate(email, password);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok("Bearer " + response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signUp(@RequestBody UserRequest userRequest) {

        try{
            UserResponse response = authService.signup(userRequest);
            Map<String,String> res=new HashMap<>();
            res.put("message","SuccessFully SignUp");
            res.put("response",response.toString());
            return new ResponseEntity<>(res, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

