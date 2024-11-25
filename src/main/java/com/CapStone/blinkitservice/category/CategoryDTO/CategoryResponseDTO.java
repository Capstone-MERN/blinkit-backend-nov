package com.CapStone.blinkitservice.category.CategoryDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDTO {
    private Integer categoryId;
    private String imageUrl;
    private String title;
    private Integer defaultSubcategory;

}

