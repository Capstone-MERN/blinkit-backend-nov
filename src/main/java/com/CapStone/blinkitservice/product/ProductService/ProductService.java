package com.CapStone.blinkitservice.product.ProductService;

import com.CapStone.blinkitservice.product.ProductRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private  final ProductRepository productRepository;
}
