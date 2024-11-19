package com.CapStone.blinkitservice.order.entity;

import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.order.enums.DeliveryStatus;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "timestamp", nullable = false)
    long timestamp;

    @Column(name = "total_amount_paid", nullable = false)
    float totalAmountPaid;

    @Column(name = "delivery_charge")
    Float deliveryCharge;

    @Column(name = "amount_saved")
    Float amountSaved;

    @Column(name = "ordered_location_latitude", nullable = false)
    float orderedLocationLatitude;

    @Column(name = "ordered_location_longitude", nullable = false)
    float orderedLocationLongitude;

    @Column(name = "contact_number", nullable = false)
    String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "address_book_id", nullable = false)
    AddressBookEntity addressBookEntity;



}
