package com.CapStone.blinkitservice.category;

import com.CapStone.blinkitservice.category.entity.SubCategoryEntity;
import com.CapStone.blinkitservice.category.model.CategoryResponse;
import com.CapStone.blinkitservice.category.model.CategoryVsSubCategoryProjection;
import com.CapStone.blinkitservice.category.model.DefaultSubcategory;
import com.CapStone.blinkitservice.category.model.SubCategoryResponse;
import com.CapStone.blinkitservice.category.repository.SubCategoryRepository;
import com.CapStone.blinkitservice.controlleradvice.exceptions.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<CategoryResponse> getCategories() {

       try{
           List<CategoryVsSubCategoryProjection> categoryList=subCategoryRepository.getAllCategoryAndDefaultSubCategory();
           return buildCategoryResponse(categoryList);
       }
       catch (Exception e){
           throw new InternalServerException("Internal Server Error.Please try after sometime");
       }

    }

    private List<CategoryResponse> buildCategoryResponse(List<CategoryVsSubCategoryProjection> categoryList){
        List<CategoryResponse> categoriesResponseList = new ArrayList<>();

        for(CategoryVsSubCategoryProjection category:categoryList){

            DefaultSubcategory defaultSubcategory=DefaultSubcategory.builder()
                    .id(category.getSubCategoryId())
                    .title(category.getSubCategoryTitle())
                    .build();
            CategoryResponse categoryResponse=CategoryResponse.builder()
                    .categoryId(category.getCategoryId())
                    .title(category.getCategoryTitle())
                    .imageUrl(category.getCategoryImageUrl())
                    .defaultSubcategory(defaultSubcategory)
                    .build();

            categoriesResponseList.add(categoryResponse);
        }

        return categoriesResponseList;
    }

    public List<SubCategoryResponse> getSubCategories(int id){
        try {
            List<SubCategoryEntity> subCategoryEntities=subCategoryRepository.findAllByCategoryId(id);
            return buildSubcategoryResponse(subCategoryEntities);
        } catch (Exception e) {
            throw new InternalServerException("Internal Server Error.Please try after sometime");
        }
    }

    private List<SubCategoryResponse> buildSubcategoryResponse(List<SubCategoryEntity> subCategories){
        List<SubCategoryResponse> subCategoryResponse=new ArrayList<>();

        for(SubCategoryEntity subCategory:subCategories){

            SubCategoryResponse response=SubCategoryResponse.builder()
                    .subCategoryId(subCategory.getId())
                    .title(subCategory.getTitle())
                    .imageUrl(subCategory.getImageUrl())
                    .build();

            subCategoryResponse.add(response);
        }

        return subCategoryResponse;
    }
}
