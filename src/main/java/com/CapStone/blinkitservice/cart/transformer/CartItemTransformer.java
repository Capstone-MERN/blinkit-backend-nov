package com.CapStone.blinkitservice.cart.transformer;

import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.cart.model.CartItemRequest;
import com.CapStone.blinkitservice.cart.model.CartItemResponse;
import com.CapStone.blinkitservice.user.transformer.UserTransformer;

public class CartItemTransformer {

    public static CartItemEntity cartItemRequestToCartItem(CartItemRequest cartItemRequest){
        return CartItemEntity.builder()
                .quantity(cartItemRequest.getQuantity())
                .build();
    }

    public static CartItemResponse cartItemToCartItemResponse(CartItemEntity cartItem){
        return CartItemResponse.builder()
                .quantity(cartItem.getQuantity())
                .userResponse(UserTransformer.userToUserResponse(cartItem.getUserEntity()))
//                .productResponse(ProductTransformer.productTOProductResponse(cartItem.getProductEntity()))
                .build();
    }
}
