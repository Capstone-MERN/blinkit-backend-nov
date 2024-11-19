package com.CapStone.BlinkIt_BackEnd.cart;

import com.CapStone.BlinkIt_BackEnd.cart.entity.CartItem;
import com.CapStone.BlinkIt_BackEnd.cart.model.CartDashboard;
import com.CapStone.BlinkIt_BackEnd.cart.model.CartItemResponse;
import com.CapStone.BlinkIt_BackEnd.cart.transformer.CartItemTransformer;
import com.CapStone.BlinkIt_BackEnd.user.UserRepository;
import com.CapStone.BlinkIt_BackEnd.user.entity.User;
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

    public CartDashboard getMyCart(String mobileNumber) {
        User user = userRepository.findByMobileNumber(mobileNumber);

        List<CartItem> itemList = cartItemRepository.findByUser(user);
        List<CartItemResponse> itemListResponse = new ArrayList<>();
        Float totalAmount = null;
        for(CartItem item: itemList){
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
