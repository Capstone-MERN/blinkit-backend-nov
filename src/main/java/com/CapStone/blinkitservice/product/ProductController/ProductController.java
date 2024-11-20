package com.CapStone.blinkitservice.product.ProductController;

import com.CapStone.blinkitservice.product.ProductService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
}
