package com.CapStone.blinkitservice.category;

import com.CapStone.blinkitservice.category.model.CategoryResponse;
import com.CapStone.blinkitservice.category.model.SubCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getCategories(){
       List<CategoryResponse> categoriesResponse = categoryService.getCategories();
       return ResponseEntity.ok().body(categoriesResponse);
    }

    @GetMapping("/getSubCategories")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategory(@RequestParam("categoryId") int categoryId){
        List<SubCategoryResponse> subCategories=categoryService.getSubCategories(categoryId);
        return ResponseEntity.ok(subCategories);
    }

}
