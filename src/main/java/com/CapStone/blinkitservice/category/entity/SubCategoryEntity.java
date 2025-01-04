package com.CapStone.blinkitservice.category.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="sub_categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubCategoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title", nullable = false, unique = true)
    String title;

    @Column(name = "image_url")
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity categoryEntity;
}