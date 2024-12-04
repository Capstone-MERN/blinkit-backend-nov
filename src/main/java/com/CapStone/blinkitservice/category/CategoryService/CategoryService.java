package com.CapStone.blinkitservice.category.CategoryService;

import com.CapStone.blinkitservice.category.CategoryDTO.CategoryResponseDTO;
import com.CapStone.blinkitservice.category.CategoryDTO.DefaultSubcategoryDTO;
import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import com.CapStone.blinkitservice.category.CategoryRepository.CategoryRepository;
import com.CapStone.blinkitservice.category.CategoryEntity.SubCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getCategories() {

        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> list = new ArrayList<>();

        for(CategoryEntity key : categories){

            SubCategoryEntity firstSubcategory = new SubCategoryEntity();

            // checking if there is the subcategory exist or not for the category entity
            if(!key.getSubCategoryEntities().isEmpty()){
                firstSubcategory =  key.getSubCategoryEntities().get(0);
            }

            // set the value to the response dto
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setCategoryId(key.getId());
            categoryResponseDTO.setTitle(key.getTitle());
            categoryResponseDTO.setImageUrl(key.getImage_url());

            // creating the default subcategory and setting the value
            DefaultSubcategoryDTO  firstDefaultSubcategory = new DefaultSubcategoryDTO();
            if (firstSubcategory != null) {
                firstDefaultSubcategory.setId(firstSubcategory.getId());
                firstDefaultSubcategory.setTitle(firstSubcategory.getTitle());
            } else {
                firstDefaultSubcategory.setId(null);
                firstDefaultSubcategory.setTitle(null);
            }

            categoryResponseDTO.setDefaultSubcategory(firstDefaultSubcategory);
            list.add(categoryResponseDTO);
        }

        return  list;

    }

}
