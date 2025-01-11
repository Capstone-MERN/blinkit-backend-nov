package com.CapStone.blinkitservice.product.model;

import com.CapStone.blinkitservice.common.response.GenericResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductSearchResponse implements GenericResponse {
    boolean hasNextPage;
    int pageNumber;
    int size;
    List<ProductResponse> products;
}
