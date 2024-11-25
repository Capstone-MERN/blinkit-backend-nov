package com.CapStone.blinkitservice.category.CategoryController;

import com.CapStone.blinkitservice.category.CategoryDTO.CategoryResponseDTO;
import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import com.CapStone.blinkitservice.category.CategoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/category/v1")
@RequiredArgsConstructor
public class CategoryController {

    private  final  CategoryService categoryService;


    @GetMapping("/get")
    public List<CategoryResponseDTO> getCategories(){

       List<CategoryResponseDTO> response = categoryService.getCategories();

        return  response;
    }


    @GetMapping("/check")
    public  String check(){
        return  "this api is working";
    }
}
