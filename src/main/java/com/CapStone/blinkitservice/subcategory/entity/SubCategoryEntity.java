package com.CapStone.blinkitservice.subcategory.entity;


import com.CapStone.blinkitservice.category.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="sub_categories")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity categoryEntity;
}