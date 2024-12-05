package com.CapStone.blinkitservice.collections;

import com.CapStone.blinkitservice.cart.CartService;
import com.CapStone.blinkitservice.collections.entity.CollectionEntity;
import com.CapStone.blinkitservice.collections.model.CollectionResponse;
import com.CapStone.blinkitservice.collections.model.CollectionsResponse;
import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionsService {

    @Autowired
    CollectionsRepository collectionsRepository;

    @Autowired
    CollectionProductsRepository collectionProductsRepository;

    @Autowired
    CartService cartService;

    @Autowired
    UserRepository userRepository;

    public CollectionsResponse getActiveCollections(String userEmail) {

        List<CollectionEntity> activeCollections = collectionsRepository.getActiveCollections();
        List<CollectionResponse> collections = new ArrayList<>();

        for (CollectionEntity collection : activeCollections){

            List<ProductEntity> products = collectionProductsRepository.findProductsByCollectionId(collection.getId());
            List<ProductResponseDto> productsResponse = new ArrayList<>();

            boolean loggedInStatus = false;
            UserEntity currUser = null;
            if(userEmail != null) {
                currUser = userRepository.findByEmail(userEmail);
                loggedInStatus = true;
            }

            for(ProductEntity product : products){
                productsResponse.add(ProductResponseDto.builder()
                                .title(product.getName())
                                .discountedPercent(product.getPrice() - (product.getPrice() * product.getDiscount()) / 100)
                                .imageUrl(product.getImageUrl())
                                .maxQuantity(product.getMaxOrderLimit())
                                .quantity(loggedInStatus == false ? null : cartService.getProductQuantityInCart(currUser.getId(),product.getId()))                                    // TODO from cart
                                .description(product.getDescription())
                                .discountPercent(product.getDiscount())
                                .originalPrice(product.getPrice())
                                .build()
                        );
            }

            collections.add(CollectionResponse.builder()
                    .collectionId(collection.getId())
                    .collectionTitle(collection.getTitle())
                    .products(productsResponse)
                    .build()
            );
        }

        return CollectionsResponse.builder()
                .collections(collections)
                .build();
    }
}
