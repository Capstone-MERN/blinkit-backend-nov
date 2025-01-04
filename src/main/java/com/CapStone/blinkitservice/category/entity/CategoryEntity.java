package com.CapStone.blinkitservice.category.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "image_url", nullable = false)
    String imageUrl;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubCategoryEntity> subCategoryEntities;
}
