package com.CapStone.blinkitservice.category.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SubCategoryResponse {

    private Integer subCategoryId;
    private String imageUrl;
    private String title;
}
