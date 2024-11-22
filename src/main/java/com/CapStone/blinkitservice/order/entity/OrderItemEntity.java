package com.CapStone.blinkitservice.order.entity;

import com.CapStone.blinkitservice.product.ProductEntity;
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
    Integer id;

    @Column(name = "quantity", nullable = false)
    int quantity;

    @Column(name = "amount_paid", nullable = false)
    Float amountPaid;

    @Column(name = "discount")
    Float discount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    ProductEntity productEntity;
}
