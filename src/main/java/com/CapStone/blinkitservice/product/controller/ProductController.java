package com.CapStone.blinkitservice.product.controller;

import com.CapStone.blinkitservice.product.dto.ProductDetailResponseDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchRequestDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import com.CapStone.blinkitservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/products/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/search")
    public ResponseEntity<ProductSearchResponseDto> searchProducts(Pageable pageable, @RequestBody ProductSearchRequestDto productSearchRequestDto,
                                                                   @AuthenticationPrincipal String userEmail){

        ProductSearchResponseDto productSearchResponseDto;
        if(productSearchRequestDto.getQuery() != null){
            productSearchResponseDto = productService.querySearch(productSearchRequestDto.getQuery(), pageable, userEmail);
        }else {
            productSearchResponseDto = productService.categorySearch(productSearchRequestDto.getCategoryId(),
                    productSearchRequestDto.getSubCategoryId(), productSearchRequestDto.getFilter(), pageable, userEmail);
        }

        return new ResponseEntity<>(productSearchResponseDto, HttpStatus.OK);
    }

    @GetMapping("details")
    public ResponseEntity<Object> productDetail(@RequestParam int id, @AuthenticationPrincipal String userEmail){

        ProductDetailResponseDto productDetailResponseDto;

        try {
            productDetailResponseDto = productService.productDetail(id, userEmail);

            return new ResponseEntity<>(productDetailResponseDto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
