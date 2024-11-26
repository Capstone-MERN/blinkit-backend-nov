package com.CapStone.blinkitservice.category.CategoryDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    Integer id;
    String title;
    String image_url;

}
