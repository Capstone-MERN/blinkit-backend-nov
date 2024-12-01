package com.CapStone.blinkitservice.product.service;

import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.product.enums.SearchFilters;
import com.CapStone.blinkitservice.product.repository.ProductRepository;
import com.CapStone.blinkitservice.product.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTransformer productTransformer;


    public ProductSearchResponseDto querySearch(String query, Pageable pageable){

        Page<ProductEntity> products = productRepository.findAllProductsByQuery(query, pageable);
        return productTransformer.createProductSearchResponse(products);
    }

    public ProductSearchResponseDto categorySearch(Integer categoryId, Integer subCategoryId, SearchFilters filter, Pageable pageable){

        Page<ProductEntity> products = productRepository.findAllProductsByFilter(subCategoryId, filter.name(), pageable);

        return productTransformer.createProductSearchResponse(products);

    }


}
