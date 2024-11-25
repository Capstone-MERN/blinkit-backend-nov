package com.CapStone.blinkitservice.subcategory.SubcategryEntity;


import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    String image_url;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;
}
