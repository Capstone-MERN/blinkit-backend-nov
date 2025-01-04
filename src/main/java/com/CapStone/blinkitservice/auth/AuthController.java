package com.CapStone.blinkitservice.auth;

import com.CapStone.blinkitservice.auth.model.SignInResponse;
import com.CapStone.blinkitservice.auth.model.SigninRequest;
import com.CapStone.blinkitservice.auth.model.SignupResponse;
import com.CapStone.blinkitservice.auth.model.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SigninRequest signinRequest) {
        String token = authService.authenticate(signinRequest);
        SignInResponse response = SignInResponse.builder()
                .message("Login success")
                .token(token)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signUp(@Valid @RequestBody SignupRequest signupRequest) {
        SignupResponse response = authService.signup(signupRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // TODO: make this specific to signup api as we may create new apis
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SignupResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder("Signup failed: ");

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        return new ResponseEntity<>(SignupResponse.builder()
                .message(errorMessage.toString())
                .build(), HttpStatus.BAD_REQUEST);
    }
}