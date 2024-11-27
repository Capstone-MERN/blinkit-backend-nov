package com.CapStone.blinkitservice.product.controller;

import com.CapStone.blinkitservice.product.dto.ProductSearchRequestDto;
import com.CapStone.blinkitservice.product.dto.ProductSearchResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/products/v1")
public class ProductController {



    @PostMapping("/search")
    public ResponseEntity<ProductSearchResponseDto> searchProducts(Pageable pageable, @RequestBody ProductSearchRequestDto productSearchRequestDto){




    }
}
