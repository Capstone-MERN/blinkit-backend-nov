package com.CapStone.blinkitservice.collections.model;

import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionResponse {

    int collectionId;

    String collectionTitle;

    List<ProductResponseDto> products;

}
