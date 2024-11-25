package com.CapStone.blinkitservice.category.CategoryController;

import com.CapStone.blinkitservice.category.CategoryDTO.CategoryResponseDTO;
import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import com.CapStone.blinkitservice.category.CategoryService.CategoryService;
import com.CapStone.blinkitservice.subcategory.SubcategryEntity.SubCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/auth/category/v1")
@RequiredArgsConstructor
public class CategoryController {

    private  final  CategoryService categoryService;


    @GetMapping
    public List<CategoryResponseDTO> getCategories(){

       List<CategoryResponseDTO> response = categoryService.getCategories();

        return  response;
    }

    @PostMapping
    public String addCategory(@RequestBody CategoryEntity categoryEntity){
        return  categoryService.addCategory(categoryEntity);
    }


    @GetMapping("/check")
    public  String check(){
        return  "this api is working";
    }
}
