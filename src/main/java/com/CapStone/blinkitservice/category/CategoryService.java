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

        for(CategoryEntity category : categories) {

            SubCategoryEntity subCategory = category.getSubCategoryEntities().getFirst();

            DefaultSubcategory defaultSubCategory = DefaultSubcategory.builder()
                    .id(subCategory.getId())
                    .title(subCategory.getTitle())
                    .build();

            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .categoryId(category.getId())
                    .title(category.getTitle())
                    .imageUrl(category.getImageUrl())
                    .defaultSubcategory(defaultSubCategory)
                    .build();

            categoriesResponseList.add(categoryResponse);
        }

        return  categoriesResponseList;
    }
}
