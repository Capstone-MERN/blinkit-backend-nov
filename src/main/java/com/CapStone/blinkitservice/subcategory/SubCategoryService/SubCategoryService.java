package com.CapStone.blinkitservice.subcategory.SubCategoryService;

import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import com.CapStone.blinkitservice.category.CategoryRepository.CategoryRepository;
import com.CapStone.blinkitservice.subcategory.SubcategoryDTO.SubcategoryRequest;
import com.CapStone.blinkitservice.subcategory.SubcategoryRepository.SubcategoryRepository;
import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private  final SubcategoryRepository subcategoryRepository;

    private  final CategoryRepository categoryRepository;

    public String addSubCategory(SubcategoryRequest subcategoryRequest) {
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity();

        CategoryEntity category = categoryRepository.findById(subcategoryRequest.getCategoryId()).get();

        subCategoryEntity1.setCategory(category);
        subCategoryEntity1.setTitle(subcategoryRequest.getTitle());
        subCategoryEntity1.setImage_url(subcategoryRequest.getImage_url());

        subcategoryRepository.save(subCategoryEntity1);

        return  "added subCategory";
    }
}
