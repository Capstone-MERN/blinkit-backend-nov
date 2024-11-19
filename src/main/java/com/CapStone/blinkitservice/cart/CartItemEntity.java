package com.CapStone.blinkitservice.cart;


import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cart_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "quantity")
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userEntity;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    Product product
}
