package com.CapStone.blinkitservice.product;

import com.CapStone.blinkitservice.cart.CartService;
import com.CapStone.blinkitservice.product.model.ProductDetailResponse;
import com.CapStone.blinkitservice.product.model.ProductSearchResponse;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.product.enums.SearchFilters;
import com.CapStone.blinkitservice.product.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartService cartService;



    public ProductSearchResponse querySearch(String query, Pageable pageable, String userEmail){

        Page<ProductEntity> products = productRepository.findAllProductsByQuery(query, pageable);
        HashMap<Integer, Integer> quantities =  cartService.getProductVsQuantityInCartByUserEmail(userEmail);
        return ProductTransformer.createProductSearchResponse(products, quantities);
    }

    public ProductSearchResponse categorySearch(Integer subCategoryId, SearchFilters filter, Pageable pageable, String userEmail){

        Page<ProductEntity> products = productRepository.findAllProductsByFilter(subCategoryId, filter.name(), pageable);
        HashMap<Integer, Integer> quantities =  cartService.getProductVsQuantityInCartByUserEmail(userEmail);
        return ProductTransformer.createProductSearchResponse(products, quantities);

    }


    public ProductDetailResponse productDetail(int id, String userEmail) throws Exception {

        ProductEntity product = productRepository.findById(id).get();
        HashMap<Integer, Integer> quantities =  cartService.getProductVsQuantityInCartByUserEmail(userEmail);
        int quantity = quantities.get(id) != null ? quantities.get(id) : 0;
        return ProductTransformer.productToProductDetailResponse(product, quantity);
    }
}
