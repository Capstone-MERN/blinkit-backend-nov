package com.CapStone.blinkitservice.product.ProductEntity;


import com.CapStone.blinkitservice.brand.BrandEntity.BrandEntity;
import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @JoinColumn(nullable = false)
    String name;
    String description;
    String unit;

    @JoinColumn(nullable = false)
    String image_url;
    Integer max_order_limit;

    @JoinColumn(nullable = false)
    Double price;

    Double discount;
    boolean isAvailable;
    String ingredients;
    String packaging_type;
    String key_features;

    @ManyToOne
    @JoinColumn
    SubCategoryEntity subCategory;

    @ManyToOne
    @JoinColumn
    BrandEntity brand;

}
