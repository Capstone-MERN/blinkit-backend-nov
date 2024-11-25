package com.CapStone.blinkitservice.subcategory.SubcategoryController;

import com.CapStone.blinkitservice.subcategory.SubCategoryService.SubCategoryService;
import com.CapStone.blinkitservice.subcategory.SubcategoryDTO.SubcategoryRequest;
import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/subCategory/v1")
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public  String addSubCategory(@RequestBody SubcategoryRequest subcategoryRequest){
        return  subCategoryService.addSubCategory(subcategoryRequest);
    }
}
