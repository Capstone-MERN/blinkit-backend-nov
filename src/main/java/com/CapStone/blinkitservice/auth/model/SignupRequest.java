package com.CapStone.blinkitservice.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;



@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {

    @NotBlank(message = "Name cannot be empty")
    String name;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    String email;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must contain only digits, and must be exactly 10 digits")
    String mobileNumber;
}
