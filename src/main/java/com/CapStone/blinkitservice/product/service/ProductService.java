package com.CapStone.blinkitservice.product.service;

import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.enums.SearchFilters;
import com.CapStone.blinkitservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //TODO - ayush
    public ProductSearchResponseDto querySearch(String query){



    }


    //TODO - vishal
    public ProductSearchResponseDto categorySearch(Integer categoryId, Integer subCategoryId, SearchFilters filter){




    }



}
