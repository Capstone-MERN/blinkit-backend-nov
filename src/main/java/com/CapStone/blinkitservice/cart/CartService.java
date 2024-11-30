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

    public UpdateCartResponse updateCart(UpdateCartRequest updateCartRequest, String userEmail) {

        UserEntity user = userRepository.findByEmail(userEmail);

        //to update in database, adding all existing products of user in cart to hashset
        List<CartItemEntity> cartItemOfUser = cartRepository.findByUserEntity(user);

        if(cartItemOfUser.isEmpty()) {
            //throw new InvalidRequestFormatException
        }

        HashSet<Integer> backendProductIds = new HashSet<>();
        for (CartItemEntity item : cartItemOfUser) {
            backendProductIds.add(item.getProductEntity().getId());
        }

        List<CartRequest> requestProductIds = updateCartRequest.getItems();

        for(CartRequest cartRequest : requestProductIds){
           if(!backendProductIds.contains(cartRequest.getProductId())){
               // throw new ProductIdNotExistException
           }
        }

        //initializing empty values for response
        List<UpdateCartProductResponse> productResponses = new ArrayList<>();
        Float totalWithoutDiscount = Float.valueOf(0.0f);
//        Float handlingCharges = Float.valueOf(0.0f);
        Float grandTotal = Float.valueOf(0.0f);
        int uniqueQuantity = 0;
        int totalQuantity = 0;


        for(CartRequest cartRequest : requestProductIds) {
            Optional<ProductEntity> productEntityOptional = productRepository.findById(cartRequest.getProductId());

            ProductEntity productEntity = productEntityOptional.get();


            //******* To Update in Database
                CartItemEntity cartItem = cartRepository.findByUserEntityAndProductEntity(user,productEntity);
                cartItem.setQuantity(cartRequest.getQuantity());
                cartRepository.save(cartItem);
                backendProductIds.remove(cartItem.getProductEntity().getId());



            Double discountApplied = 0.0;
            if(productEntity.getDiscount() != null && productEntity.getDiscount() != 0.0){
                discountApplied = (productEntity.getPrice()*(productEntity.getDiscount()/100.0));
            }

            //******* To Respond to Client
            UpdateCartProductResponse productResponse = UpdateCartProductResponse.builder()
                    .quantity(cartRequest.getQuantity())
                    .productId(productEntity.getId())
                    .name(productEntity.getName())
                    .imageUrl(productEntity.getImageUrl())
                    .originalPrice(productEntity.getPrice())
                    .discountedPrice(productEntity.getPrice() - discountApplied)
                    .maxOrderLimit(productEntity.getMaxOrderLimit())
                    .description(productEntity.getDescription())
                    .unit(productEntity.getUnit())
                    .isAvailable(productEntity.isAvailable())
                    .build();

            totalWithoutDiscount += productEntity.getPrice().floatValue();
            grandTotal += productResponse.getDiscountedPrice().floatValue();
            uniqueQuantity += 1;
            totalQuantity += cartRequest.getQuantity();
            productResponses.add(productResponse);
        }

        //******* To Remove from Database, which was removed from cart
        for(int productId : backendProductIds){
            CartItemEntity toDelete = cartRepository.findByProductIdAndUserId(productId, user.getId());
            cartRepository.delete(toDelete);
        }


        UpdateCartResponse response = UpdateCartResponse.builder()
                .products(productResponses)
                .totalWithoutDiscount(totalWithoutDiscount)
                .grandTotal(grandTotal)
//                .handlingCharges(4.0f)
                .uniqueQuantity(uniqueQuantity)
                .totalQuantity(totalQuantity)
                .build();

        return response;
    }
}
