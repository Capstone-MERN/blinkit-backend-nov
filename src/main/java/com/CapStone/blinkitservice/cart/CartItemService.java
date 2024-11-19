package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.cart.model.CartDashboard;
import com.CapStone.blinkitservice.cart.model.CartItemResponse;
import com.CapStone.blinkitservice.cart.transformer.CartItemTransformer;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    public CartDashboard getMyCart(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        List<CartItemEntity> itemList = cartItemRepository.findByUserEntity(userEntity);
        List<CartItemResponse> itemListResponse = new ArrayList<>();
        Float totalAmount = null;
        for(CartItemEntity item: itemList){
            itemListResponse.add(CartItemTransformer.cartItemToCartItemResponse(item));
            //totalAmount += item.product.getPrice()*item.getQuantity();
        }

        return CartDashboard.builder()
                .cartItemResponseList(itemListResponse)
                //.handlingCharge()
                //.deliveryCharge()
                //.estimatedTime()
                //.grandTotal(totalAmount)
                .quantity(itemList.size())
                .build();

    }
}
