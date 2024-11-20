package com.CapStone.blinkitservice.category.CategoryController;

import com.CapStone.blinkitservice.category.CategoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CategoryController {

    private  final  CategoryService categoryService;


}
