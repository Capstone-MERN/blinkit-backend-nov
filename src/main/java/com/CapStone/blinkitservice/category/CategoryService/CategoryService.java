package com.CapStone.blinkitservice.category.CategoryService;

import com.CapStone.blinkitservice.category.CategoryDTO.CategoryResponseDTO;
import com.CapStone.blinkitservice.category.CategoryDTO.DefaultSubcategoryDTO;
import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import com.CapStone.blinkitservice.category.CategoryRepository.CategoryRepository;
import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getCategories() {

        List<CategoryEntity> categories = categoryRepository.findAllWithSubcategories();

        return  categories.stream().map( categoryEntity -> {
            DefaultSubcategoryDTO defaultSubcategory = null;
            if (categoryEntity.getSubCategoryEntities() != null && !categoryEntity.getSubCategoryEntities().isEmpty()){
                SubCategoryEntity firstSubcategory = categoryEntity.getSubCategoryEntities().get(0);
                defaultSubcategory.setId(firstSubcategory.getId());
            }
            return new CategoryResponseDTO(
                    categoryEntity.getId(),
                    categoryEntity.getImage_url(),
                    categoryEntity.getTitle(),
                    defaultSubcategory
            );
        }).collect(Collectors.toList());
    }
}
