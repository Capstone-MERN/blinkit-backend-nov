package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.cart.model.CartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartProductResponse;
import com.CapStone.blinkitservice.cart.model.UpdateCartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartResponse;
import com.CapStone.blinkitservice.common.error.exception.InvalidCartPayloadResponse;
import com.CapStone.blinkitservice.product.ProductRepository;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.product.model.ProductMaxOrderProjection;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public UpdateCartResponse updateCart(UpdateCartRequest updateCartRequest, String userEmail) throws InvalidCartPayloadResponse {

        List<CartRequest> requestItems = updateCartRequest.getItems();
        List<Integer> productIds = validateCartInfo(requestItems);

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

        cartRepository.removeNonExistingProductsFromCart(user.getId(), productIds);

        List<CartItemEntity> userCartItems = cartRepository.findByUserEntity(user);

        syncWithBackendCartItems(userCartItems,requestItems,user);

        cartRepository.saveAll(userCartItems);

        return buildUpdateCartResponse(userCartItems);

    }

    private List<Integer> validateCartInfo(List<CartRequest> requestItems) throws InvalidCartPayloadResponse {

        Map<Integer, Integer> requestCartMap = new HashMap<>();
        List<Integer> productIds=new ArrayList<>();

        requestItems.forEach(cartRequest -> {
            if(cartRequest.getQuantity()<=0){
                throw new InvalidCartPayloadResponse("The given quantity is less than one for product id " + cartRequest.getProductId());
            }
            if(requestCartMap.containsKey(cartRequest.getProductId())){
                throw new InvalidCartPayloadResponse("ProductId " + cartRequest.getProductId() + " is Duplicated");
            }
            productIds.add(cartRequest.getProductId());
            requestCartMap.put(cartRequest.getProductId(), cartRequest.getQuantity());
        });

        List<ProductMaxOrderProjection> productsWithMaxQuantity =productRepository.findMaxOrderLimitByProductIds(productIds);

        if(productsWithMaxQuantity.size()!= requestCartMap.size())  throw new InvalidCartPayloadResponse("Invalid product ids");

        for(ProductMaxOrderProjection productEntity:productsWithMaxQuantity){

            if(requestCartMap.get(productEntity.getId())>productEntity.getMaxOrderLimit()){
                throw new InvalidCartPayloadResponse("The given quantity is exceeding maximum limit for productId "+ productEntity.getId());
            }
        }

        return productIds;

    }

    private void syncWithBackendCartItems(List<CartItemEntity> userCartItems,List<CartRequest> requestList,UserEntity user) throws InvalidCartPayloadResponse {

        for(CartRequest cartRequest:requestList){

            Optional<CartItemEntity> cartResult = userCartItems.stream()
                    .filter(cart->cart.getProductEntity().getId()==cartRequest.getProductId()).findFirst();

            if(cartResult.isPresent()){
                CartItemEntity cartItem=cartResult.get();
                cartItem.setQuantity(cartRequest.getQuantity());
            }
            else{
              
                ProductEntity productEntity=productRepository.getReferenceById(cartRequest.getProductId());
                CartItemEntity newCartItem= CartItemEntity.builder()
                        .userEntity(user)
                        .quantity(cartRequest.getQuantity())
                        .productEntity(productEntity)
                        .build();
                userCartItems.add(newCartItem);
            }
        }
    }

    private UpdateCartResponse buildUpdateCartResponse(List<CartItemEntity> userCartItems){

        List<UpdateCartProductResponse> productResponses = new ArrayList<>();
        Float totalWithoutDiscount = Float.valueOf(0.0f);
        Float grandTotal = Float.valueOf(0.0f);
        int uniqueQuantity = 0;
        int totalQuantity = 0;

        for(CartItemEntity cartItem:userCartItems){
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





    public int getProductQuantityInCart(int userId, int productId){

        return cartRepository.getProductQuantityInCart(userId, productId);
    }


}
