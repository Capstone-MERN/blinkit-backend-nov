package com.CapStone.blinkitservice.category.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Integer categoryId;
    private String imageUrl;
    private String title;
    private DefaultSubcategory defaultSubcategory;
}

