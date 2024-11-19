package com.CapStone.BlinkIt_BackEnd.cart.transformer;

import com.CapStone.BlinkIt_BackEnd.cart.entity.CartItem;
import com.CapStone.BlinkIt_BackEnd.cart.model.CartItemRequest;
import com.CapStone.BlinkIt_BackEnd.cart.model.CartItemResponse;

public class CartItemTransformer {

    public static CartItem cartItemRequestToCartItem(CartItemRequest cartItemRequest){
        return CartItem.builder()
                .quantity(cartItemRequest.getQuantity())
                .build();
    }

    public static CartItemResponse cartItemToCartItemResponse(CartItem cartItem){
        return CartItemResponse.builder()
                .quantity(cartItem.getQuantity())
                .user(cartItem.getUser())
                //.product(cartItem.getProduct())
                .build();
    }
}
