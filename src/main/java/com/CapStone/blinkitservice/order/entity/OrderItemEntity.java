package com.CapStone.blinkitservice.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "quantity", nullable = false)
    int quantity;

    @Column(name = "amount_paid", nullable = false)
    float amountPaid;

    @Column(name = "discount")
    Float discount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    OrderEntity orderEntity;

    //@ManyToOne
    //@JoinColumn(name = "product_id", nullable = false)
    //Product product
}
