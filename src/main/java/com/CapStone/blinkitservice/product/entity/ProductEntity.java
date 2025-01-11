package com.CapStone.blinkitservice.product.entity;


import com.CapStone.blinkitservice.brand.entity.BrandEntity;
import com.CapStone.blinkitservice.category.entity.SubCategoryEntity;
import com.CapStone.blinkitservice.product.converter.JsonConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Entity
@Table(name="products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "max_order_limit")
    int maxOrderLimit;

    @Column(name = "price", nullable = false)
    Float price;

    @Column(name = "discount")
    Float discount;

    @Column(name = "is_available", nullable = false)
    boolean isAvailable;

    @Column(name = "ingredients")
    String ingredients;

    @Column(name = "packaging_type")
    String packagingType;

    @Column(name = "key_features")
    String keyFeatures;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    SubCategoryEntity subCategoryEntity;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    BrandEntity brandEntity;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    Map<String, Object> metaData;
}
