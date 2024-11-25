package com.CapStone.blinkitservice.category.CategoryService;

import com.CapStone.blinkitservice.category.CategoryDTO.CategoryResponseDTO;
import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import com.CapStone.blinkitservice.category.CategoryRepository.CategoryRepository;
import com.CapStone.blinkitservice.subcategory.SubCategoryEntity;
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
            defaultCategory =  key.getSubCategoryEntities().get(0).getId();

            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setCategoryId(key.getId());
            categoryResponseDTO.setTitle(key.getTitle());
            categoryResponseDTO.setImageUrl(key.getImage_url());
            categoryResponseDTO.setDefaultSubcategory(defaultCategory);

            list.add(categoryResponseDTO);
        }

        return  list;

//        return  categories.stream().map( categoryEntity -> {
//            DefaultSubcategoryDTO defaultSubcategory = null;
//            if (categoryEntity.getSubCategoryEntities() != null && !categoryEntity.getSubCategoryEntities().isEmpty()){
//                SubCategoryEntity firstSubcategory = categoryEntity.getSubCategoryEntities().get(0);
//                defaultSubcategory.setId(firstSubcategory.getId());//firstSubcategory.getId()
//            }
//           return new CategoryResponseDTO(
//                    categoryEntity.getId(),
//                    categoryEntity.getImage_url(),
//                    categoryEntity.getTitle(),
//                    defaultSubcategory
//            );
//        }).collect(Collectors.toList());
    }


}
