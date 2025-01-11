package com.CapStone.blinkitservice.collections.model;

import com.CapStone.blinkitservice.product.model.ProductResponse;
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

    List<ProductResponse> products;

}
