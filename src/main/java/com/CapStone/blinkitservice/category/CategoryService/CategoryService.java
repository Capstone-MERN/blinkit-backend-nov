package com.CapStone.blinkitservice.category.CategoryService;

import com.CapStone.blinkitservice.category.CategoryRepository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;
}
