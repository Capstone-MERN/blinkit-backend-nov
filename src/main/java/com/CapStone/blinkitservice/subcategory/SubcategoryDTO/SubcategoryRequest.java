package com.CapStone.blinkitservice.subcategory.SubcategoryDTO;

import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubcategoryRequest {

    String title;

    String image_url;

    Integer categoryId;
}
