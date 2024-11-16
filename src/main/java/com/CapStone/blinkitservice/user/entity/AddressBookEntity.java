package com.CapStone.blinkitservice.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "address_book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressBookEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "latitude", nullable = false)
    float latitude;

    @Column(name = "longitude", nullable = false)
    float longitude;

    @Column(name = "address_line_1", nullable = false)
    String addressLine1;

    @Column(name = "address_line_2")
    String addressLine2;

    @Column(name = "address_line_3")
    String addressLine3;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userEntity;

}
