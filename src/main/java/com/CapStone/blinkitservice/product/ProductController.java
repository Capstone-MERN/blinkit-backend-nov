package com.CapStone.blinkitservice.product;

import com.CapStone.blinkitservice.common.error.GenericErrorResponse;
import com.CapStone.blinkitservice.common.response.GenericResponse;
import com.CapStone.blinkitservice.product.model.ProductDetailResponse;
import com.CapStone.blinkitservice.product.model.ProductSearchRequest;
import com.CapStone.blinkitservice.product.model.ProductSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/search")
    public ResponseEntity<GenericResponse> searchProducts(@RequestBody ProductSearchRequest productSearchRequest,
                                                                   @AuthenticationPrincipal String userEmail){

        try{
            ProductSearchResponse productSearchResponse;
            Pageable page=PageRequest.of(productSearchRequest.getPageNumber(),20);
            if(productSearchRequest.getQuery() != null){
                productSearchResponse = productService.querySearch(productSearchRequest.getQuery(),page,userEmail);
            }else {
                productSearchResponse = productService.categorySearch(
                        productSearchRequest.getSubCategoryId(), productSearchRequest.getFilter(),page,userEmail);
            }
            return new ResponseEntity<>(productSearchResponse, HttpStatus.OK);
        } catch (Exception e) {
           return  new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<Object> productDetail(@RequestParam int id, @AuthenticationPrincipal String userEmail){

        ProductDetailResponse productDetailResponse;

        try {
            productDetailResponse = productService.productDetail(id, userEmail);

            return new ResponseEntity<>(productDetailResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
