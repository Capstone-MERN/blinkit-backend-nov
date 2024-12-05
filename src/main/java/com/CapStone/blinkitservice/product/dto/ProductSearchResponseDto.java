package com.CapStone.blinkitservice.product.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductSearchResponseDto {

    boolean hasNextPage;
    int pageNumber;
    int size;
    List<ProductResponseDto> products;
}
