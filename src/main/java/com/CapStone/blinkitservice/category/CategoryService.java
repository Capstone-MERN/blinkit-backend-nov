package com.CapStone.blinkitservice.category;

import com.CapStone.blinkitservice.category.model.CategoryResponse;
import com.CapStone.blinkitservice.category.model.DefaultSubcategory;
import com.CapStone.blinkitservice.category.entity.CategoryEntity;
import com.CapStone.blinkitservice.category.entity.SubCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponse> getCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryResponse> categoriesResponseList = new ArrayList<>();
        SubCategoryEntity defaultSubCategory = null;

        if(!categories.isEmpty()){
            List<SubCategoryEntity> subcategories = categories.getFirst().getSubCategoryEntities();
            if(!subcategories.isEmpty()){
                defaultSubCategory = subcategories.getFirst();
            }
        }

        for(CategoryEntity category : categories){
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .categoryId(category.getId())
                    .title(category.getTitle())
                    .imageUrl(category.getImageUrl())
                    .build();
            categoriesResponseList.add(categoryResponse);
        }

        if(defaultSubCategory != null && !categoriesResponseList.isEmpty()){
            categoriesResponseList.getFirst().setDefaultSubcategory(
                    DefaultSubcategory.builder()
                            .id(defaultSubCategory.getId())
                            .title(defaultSubCategory.getTitle())
                            .build()
            );
        }

        return  categoriesResponseList;
    }
}
