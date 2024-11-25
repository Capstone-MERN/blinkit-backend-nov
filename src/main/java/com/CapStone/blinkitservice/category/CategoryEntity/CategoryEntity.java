package com.CapStone.blinkitservice.category.CategoryEntity;

import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.net.ssl.SSLSession;
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

    @JoinColumn(nullable = false)
    String title;

    @JoinColumn(nullable = false)
    String image_url;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubCategoryEntity> subCategoryEntities;


}
