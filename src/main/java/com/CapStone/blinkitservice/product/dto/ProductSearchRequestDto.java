package com.CapStone.blinkitservice.product.dto;

import com.CapStone.blinkitservice.product.enums.SearchFilters;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequestDto {
    String query;
    Integer categoryId;
    Integer subCategoryId;
    SearchFilters filter;
}
