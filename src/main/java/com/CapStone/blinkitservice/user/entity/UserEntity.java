package com.CapStone.blinkitservice.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "unique_email_field"),
        @UniqueConstraint(columnNames = "mobile_number", name = "unique_mobile_number_field")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "mobile_number", nullable = false, unique =true, length = 10)
    String mobileNumber;


}
