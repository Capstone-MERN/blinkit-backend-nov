package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.cart.model.CartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartProductResponse;
import com.CapStone.blinkitservice.cart.model.UpdateCartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartResponse;
import com.CapStone.blinkitservice.common.error.exception.InvalidCartPayloadResponse;
import com.CapStone.blinkitservice.product.ProductRepository;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UpdateCartResponse updateCartDemo(UpdateCartRequest updateCartRequest, String userEmail) throws InvalidCartPayloadResponse {

        List<CartRequest> items = updateCartRequest.getItems();
        List<Integer> productIds = extractProductIds(items);

        UserEntity user = userRepository.findByEmail(userEmail);

        if(productIds.isEmpty()){
            cartRepository.deleteAllByUserId(user.getId());

            return UpdateCartResponse.builder()
                            .products(new ArrayList<>())
                            .totalWithoutDiscount(0.0f)
                            .grandTotal(0.0f)
                            .uniqueQuantity(0)
                            .totalQuantity(0)
                            .build();
        }

        cartRepository.deleteInvalidProductsByUserIdAndProductIds(user.getId(), productIds);

        List<CartItemEntity> cartItemOfUser = cartRepository.findByUserEntity(user);

        syncWithBackendCartItems(cartItemOfUser,items,user);

        cartRepository.saveAll(cartItemOfUser);

        return buildUpdateCartResponse(cartItemOfUser);

    }

    private List<Integer> extractProductIds(List<CartRequest> items) throws InvalidCartPayloadResponse {
        List<Integer> pIds=new ArrayList<>();
        for (CartRequest cartRequest:items){
            if(cartRequest.getQuantity()<=0){
                throw new InvalidCartPayloadResponse("The given quantity is less than zero for productId "+cartRequest.getProductId());
            }
            if(pIds.contains(cartRequest.getProductId())){
                throw new InvalidCartPayloadResponse("ProductId "+cartRequest.getProductId()+" is Duplicated");
            }
            pIds.add(cartRequest.getProductId());
        }
        return pIds;
    }

    private void syncWithBackendCartItems(List<CartItemEntity> cartItemOfUser,List<CartRequest> requestList,UserEntity user) throws InvalidCartPayloadResponse {

        for(CartRequest cartRequest:requestList){

            Optional<CartItemEntity> cartResult=cartItemOfUser.stream()
                    .filter(cart->cart.getProductEntity().getId()==cartRequest.getProductId()).findFirst();

            Optional<ProductEntity> productResult=productRepository.findById(cartRequest.getProductId());
            if(productResult.isEmpty()){
                throw new InvalidCartPayloadResponse("productId "+cartRequest.getProductId()+" does not exist");
            }

            ProductEntity productEntity=productResult.get();

            if(productEntity.getMaxOrderLimit()<cartRequest.getQuantity()){
                throw new InvalidCartPayloadResponse("The given quantity is exceeding maximum limit for productId "+cartRequest.getProductId());
            }
            if(cartResult.isPresent()){
                CartItemEntity cartItem=cartResult.get();
                cartItem.setQuantity(cartRequest.getQuantity());
            }
            else{
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

            totalWithoutDiscount += productEntity.getPrice().floatValue()*cartItem.getQuantity();
            grandTotal += productResponse.getDiscountedPrice().floatValue()*cartItem.getQuantity();
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
