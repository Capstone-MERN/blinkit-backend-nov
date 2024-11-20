package com.CapStone.blinkitservice.brand.BrandService;

import com.CapStone.blinkitservice.brand.BrandRepository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private  final BrandRepository brandRepository;
}
