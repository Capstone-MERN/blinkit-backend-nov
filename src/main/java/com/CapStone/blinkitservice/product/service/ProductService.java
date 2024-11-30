package com.CapStone.blinkitservice.product.service;

import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.product.enums.SearchFilters;
import com.CapStone.blinkitservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductSearchResponseDto querySearch(String query, Pageable pageable){

        Page<ProductEntity> products = productRepository.findAllProductsByQuery(query, pageable);

        ProductSearchResponseDto productSearchResponse = new ProductSearchResponseDto();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(ProductEntity product : products.getContent()){
            ProductResponseDto responseDto = new ProductResponseDto();
            responseDto.setTitle(product.getName());
            responseDto.setPrice(product.getPrice());
            responseDto.setImageUrl(product.getImageUrl());
            responseDto.setMaxQuantity(product.getMaxOrderLimit());
            responseDto.setQuantity(0);
            responseDto.setDescription(product.getDescription());
            responseDto.setDiscountPercent(product.getDiscount());
            responseDto.setOriginalPrice(product.getPrice());

            productResponseDtoList.add(responseDto);
        }

        productSearchResponse.setProducts(productResponseDtoList);
        productSearchResponse.setSize(products.getSize());
        productSearchResponse.setTotalPageNumber(products.getTotalPages());
        productSearchResponse.setPageNumber(products.getNumber());
        productSearchResponse.setCount(products.getTotalElements());

        return productSearchResponse;
    }


    public ProductSearchResponseDto categorySearch(Integer categoryId, Integer subCategoryId, SearchFilters filter, Pageable pageable){

        Page<ProductEntity> products = productRepository.findAllProductsByFilter(subCategoryId, filter.name(), pageable);

        ProductSearchResponseDto productSearchResponse = new ProductSearchResponseDto();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(ProductEntity product : products.getContent()){
            ProductResponseDto responseDto = new ProductResponseDto();
            responseDto.setTitle(product.getName());
            responseDto.setPrice(product.getPrice());
            responseDto.setImageUrl(product.getImageUrl());
            responseDto.setMaxQuantity(product.getMaxOrderLimit());
            responseDto.setQuantity(0);
            responseDto.setDescription(product.getDescription());
            responseDto.setDiscountPercent(product.getDiscount());
            responseDto.setOriginalPrice(product.getPrice());

            productResponseDtoList.add(responseDto);
        }

        productSearchResponse.setProducts(productResponseDtoList);
        productSearchResponse.setSize(products.getSize());
        productSearchResponse.setTotalPageNumber(products.getTotalPages());
        productSearchResponse.setPageNumber(products.getNumber());
        productSearchResponse.setCount(products.getTotalElements());

        return productSearchResponse;

    }



}
