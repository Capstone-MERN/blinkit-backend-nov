package com.CapStone.blinkitservice.category.CategoryController;

import com.CapStone.blinkitservice.category.CategoryDTO.CategoryResponseDTO;
import com.CapStone.blinkitservice.category.CategoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category/v1")
@RequiredArgsConstructor
public class CategoryController {

    private  final  CategoryService categoryService;

    @GetMapping("getCategoryList")
    public ResponseEntity<Map<String, List<CategoryResponseDTO>>> getCategories(){

        List<CategoryResponseDTO> categories  = categoryService.getCategories();
        Map<String , List<CategoryResponseDTO>> response = new HashMap<>();

        response.put("Categories" , categories);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public  String check(){
        return  "this api is working";
    }
}
