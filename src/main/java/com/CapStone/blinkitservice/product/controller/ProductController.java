package com.CapStone.blinkitservice.product.controller;

import com.CapStone.blinkitservice.product.dto.ProductSearchRequestDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/products/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/search")
    public ResponseEntity<ProductSearchResponseDto> searchProducts(Pageable pageable, @RequestBody ProductSearchRequestDto productSearchRequestDto){

        ProductSearchResponseDto productSearchResponseDto = null;
        if(productSearchRequestDto.getQuery() != null){
            productSearchResponseDto = productService.querySearch(productSearchRequestDto.getQuery(), pageable);
        }else {
            productSearchResponseDto = productService.categorySearch(productSearchRequestDto.getCategoryId(),
                    productSearchRequestDto.getSubCategoryId(), productSearchRequestDto.getFilter());
        }

        if(productSearchResponseDto == null){
            System.out.println("error");
        }

        return new ResponseEntity<>(productSearchResponseDto, HttpStatus.OK);
    }
}
