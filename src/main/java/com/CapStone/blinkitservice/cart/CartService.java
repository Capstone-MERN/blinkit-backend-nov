package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.cart.model.CartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartProductResponse;
import com.CapStone.blinkitservice.cart.model.UpdateCartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartResponse;
import com.CapStone.blinkitservice.product.ProductRepository;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public UpdateCartResponse updateCartDemo(UpdateCartRequest updateCartRequest, String userEmail) {

        List<CartRequest> items = updateCartRequest.getItems();
        List<Integer> productIds = extractProductIds(items);

        UserEntity user = userRepository.findByEmail(userEmail);

        cartRepository.deleteInvalidProductsByUserIdAndProductIds(user.getId(),productIds);

        List<CartItemEntity> cartItemOfUser = cartRepository.findByUserEntity(user);

        syncWithBackendCartItems(cartItemOfUser,items,user);

        cartRepository.saveAll(cartItemOfUser);

        return buildUpdateCartResponse(cartItemOfUser);

    }

    private List<Integer> extractProductIds(List<CartRequest> items){
        List<Integer> pIds=new ArrayList<>();
        for (CartRequest cartRequest:items){
            pIds.add(cartRequest.getProductId());
        }
        return pIds;
    }

    private void syncWithBackendCartItems(List<CartItemEntity> cartItemOfUser,List<CartRequest> requestList,UserEntity user){

        for(CartRequest cartRequest:requestList){

            Optional<CartItemEntity> result=cartItemOfUser.stream()
                    .filter(cart->cart.getProductEntity().getId()==cartRequest.getProductId()).findFirst();

            if(result.isPresent()){
                CartItemEntity cartItem=result.get();
                cartItem.setQuantity(cartRequest.getQuantity());
            }
            else{
                ProductEntity productEntity=productRepository.getReferenceById(cartRequest.getProductId());
                CartItemEntity newCartItem= CartItemEntity.builder()
                        .userEntity(user)
                        .quantity(cartRequest.getQuantity())
                        .productEntity(productEntity)
                        .build();
                cartItemOfUser.add(newCartItem);
            }
        }
    }

    private UpdateCartResponse buildUpdateCartResponse(List<CartItemEntity> cartItemOfUser){

        List<UpdateCartProductResponse> productResponses = new ArrayList<>();
        Float totalWithoutDiscount = Float.valueOf(0.0f);
        Float grandTotal = Float.valueOf(0.0f);
        int uniqueQuantity = 0;
        int totalQuantity = 0;

        for(CartItemEntity cartItem:cartItemOfUser){
            ProductEntity productEntity=cartItem.getProductEntity();

            Double discountApplied = 0.0;
            if(productEntity.getDiscount() != null){
                discountApplied = (productEntity.getPrice()*(productEntity.getDiscount()/100.0));
            }


            UpdateCartProductResponse productResponse = UpdateCartProductResponse.builder()
                    .quantity(cartItem.getQuantity())
                    .productId(productEntity.getId())
                    .name(productEntity.getName())
                    .imageUrl(productEntity.getImageUrl())
                    .originalPrice(productEntity.getPrice())
                    .discountedPrice(productEntity.getPrice() - discountApplied)
                    .maxOrderLimit(productEntity.getMaxOrderLimit())
                    .description(productEntity.getDescription())
                    .isAvailable(productEntity.isAvailable())
                    .build();

            totalWithoutDiscount += productEntity.getPrice().floatValue();
            grandTotal += productResponse.getDiscountedPrice().floatValue();
            uniqueQuantity += 1;
            totalQuantity += cartItem.getQuantity();
            productResponses.add(productResponse);
        }

        UpdateCartResponse response = UpdateCartResponse.builder()
                .products(productResponses)
                .totalWithoutDiscount(totalWithoutDiscount)
                .grandTotal(grandTotal)
                .uniqueQuantity(uniqueQuantity)
                .totalQuantity(totalQuantity)
                .build();

        return response;
    }


}
