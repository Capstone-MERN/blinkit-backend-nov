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
        Integer defaultCategory =  null;
        for(CategoryEntity key : categories){
            SubCategoryEntity firstSubcategory =  key.getSubCategoryEntities().get(0);
            defaultCategory =  firstSubcategory.getId();

            // set the value to the response dto
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setCategoryId(key.getId());
            categoryResponseDTO.setTitle(key.getTitle());
            categoryResponseDTO.setImageUrl(key.getImage_url());

            DefaultSubcategoryDTO  firstDefaultSubcategory = new DefaultSubcategoryDTO();
            firstDefaultSubcategory.setId(defaultCategory);
            firstDefaultSubcategory.setTitle(firstSubcategory.getTitle());

            categoryResponseDTO.setDefaultSubcategory(firstDefaultSubcategory);
            list.add(categoryResponseDTO);
        }

        return  list;

    }

}
