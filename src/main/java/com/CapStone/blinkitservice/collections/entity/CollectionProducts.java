package com.CapStone.blinkitservice.collections.entity;

import com.CapStone.blinkitservice.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "collection_products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionProducts {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    CollectionEntity collection;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    ProductEntity product;
}
