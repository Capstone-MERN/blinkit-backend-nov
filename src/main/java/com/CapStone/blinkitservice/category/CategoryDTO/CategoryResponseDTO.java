package com.CapStone.blinkitservice.category.CategoryDTO;

import lombok.*;

@Data
public class CategoryResponseDTO {
    private Integer categoryId;
    private String imageUrl;
    private String title;
    private Integer defaultSubcategoryId;
    private String defaultSubcategoryTitle;

}

