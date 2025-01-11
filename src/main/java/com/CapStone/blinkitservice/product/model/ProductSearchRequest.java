package com.CapStone.blinkitservice.product.model;

import com.CapStone.blinkitservice.product.enums.SearchFilters;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {
    String query;
    Integer pageNumber;
    Integer subCategoryId;
    SearchFilters filter;
}
