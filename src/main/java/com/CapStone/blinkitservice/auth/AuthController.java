package com.CapStone.blinkitservice.auth;

import com.CapStone.blinkitservice.user.model.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/api/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Now Authorized");
    }


    @PostMapping("/auth/signin")
    public ResponseEntity<String> signIn(@RequestBody AuthRequest authRequest) {

        String response = authService.authenticate(authRequest);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok("Bearer " + response);
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody UserRequest userRequest) {

        try {
            AuthResponse response = authService.signup(userRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            String duplicateField = "";

            if (e.getMessage().contains("UK6dotkott2kjsp8vw4d0m25fb7")) {
                duplicateField += "email already exist. ";
            } else if (e.getMessage().contains("UKr7c96a004bv8w16jgdm8imich")) {
                duplicateField += "mobile number already exist.";
            } else {
                duplicateField += "details already exist.";
            }
            return new ResponseEntity<>(AuthResponse.builder().message("Signup failed: Given " + duplicateField).build(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(AuthResponse.builder()
                    .message("Sorry, sign up failed")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthResponse> handleValidationExceptions(MethodArgumentNotValidException e) {

        StringBuilder errorMessage = new StringBuilder("Signup failed: ");

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        return new ResponseEntity<>(AuthResponse.builder()
                .message(errorMessage.toString())
                .build(), HttpStatus.BAD_REQUEST);
    }

}