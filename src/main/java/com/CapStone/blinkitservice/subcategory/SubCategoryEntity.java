package com.CapStone.blinkitservice.subcategory;


import com.CapStone.blinkitservice.category.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    CategoryEntity category;
}
