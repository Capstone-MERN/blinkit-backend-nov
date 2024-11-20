package com.CapStone.blinkitservice.category.CategoryEntity;

import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JoinColumn(nullable = false)
    String title;

    @JoinColumn(nullable = false)
    String image_url;

    @OneToMany
    List<SubCategoryEntity> subCategoryEntities;
}
