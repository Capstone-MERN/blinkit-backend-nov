package com.CapStone.blinkitservice.category;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CategoryEntity {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JoinColumn(nullable = false)
    String title;

//    @JoinColumn(nullable = false)
//    String image_url;
}
