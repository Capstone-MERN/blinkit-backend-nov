package com.CapStone.blinkitservice.brand;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    String logo;

    String title;
}
