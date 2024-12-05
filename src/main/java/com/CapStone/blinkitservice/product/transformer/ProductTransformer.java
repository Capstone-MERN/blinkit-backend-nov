package com.CapStone.blinkitservice.product.transformer;

import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ProductTransformer {

    public static List<ProductResponseDto> productToProductResponse(Page<ProductEntity> products) {

        return products.getContent().stream()
                .map(product -> ProductResponseDto.builder()
                        .title(product.getName())
                        .price(product.getPrice())
                        .imageUrl(product.getImageUrl())
                        .maxQuantity(product.getMaxOrderLimit())
                        .quantity(0) // TODO: Integrate cart service to set actual quantity
                        .description(product.getDescription())
                        .discountPercent(product.getDiscount())
                        .originalPrice(product.getPrice())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static ProductSearchResponseDto createProductSearchResponse(Page<ProductEntity> products){

        return ProductSearchResponseDto.builder()
                .products(productToProductResponse(products))
                .size(products.getSize())
                .pageNumber(products.getNumber())
                .hasNextPage(products.hasNext())
                .build();
    }
}

