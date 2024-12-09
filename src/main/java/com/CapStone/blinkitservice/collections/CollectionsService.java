package com.CapStone.blinkitservice.collections;

import com.CapStone.blinkitservice.cart.CartService;
import com.CapStone.blinkitservice.collections.entity.CollectionProducts;
import com.CapStone.blinkitservice.collections.model.CollectionResponse;
import com.CapStone.blinkitservice.collections.model.CollectionsResponse;
import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionsService {

    @Autowired
    CollectionProductsRepository collectionProductsRepository;

    @Autowired
    CartService cartService;

    @Autowired
    UserRepository userRepository;

    public CollectionsResponse getActiveCollections(String userEmail) {

        List<CollectionProducts> collectionProducts = collectionProductsRepository.findActiveCollectionProducts();

        Map<Integer,List<ProductEntity>> collectionMap=new HashMap<>();

        List<CollectionResponse> collections = new ArrayList<>();

        for(CollectionProducts collectionProduct:collectionProducts){

            int key=collectionProduct.getCollection().getId();

            if(!collectionMap.containsKey(key)){
                collectionMap.put(key,new ArrayList<>());
                collections.add(CollectionResponse.builder()
                        .collectionId(key)
                        .collectionTitle(collectionProduct.getCollection().getTitle())
                        .build());
            }
            List<ProductEntity> products=collectionMap.get(key);
            products.add(collectionProduct.getProduct());
            collectionMap.put(key,products);

        }

        buildCollectionsResponse(collectionMap,collections);

        return CollectionsResponse.builder()
                .collections(collections)
                .build();
    }

    private void buildCollectionsResponse(Map<Integer,List<ProductEntity>> collectionMap,List<CollectionResponse> collections){

        for(CollectionResponse collection:collections){

            List<ProductEntity> products=collectionMap.get(collection.getCollectionId());

            List<ProductResponseDto> productsResponse = new ArrayList<>();

            for(ProductEntity product: products){

                Double discount=(product.getDiscount()==null)?0:product.getDiscount();

                productsResponse.add(ProductResponseDto.builder()
                        .title(product.getName())
                        .price(product.getPrice() - (product.getPrice() * discount) / 100)
                        .imageUrl(product.getImageUrl())
                        .maxQuantity(product.getMaxOrderLimit())
                        .quantity(0)     // TODO from cart
                        //need to fetch logged in user incase of logged in, else to provide 0 in quantity
                        // jwt to be altered to handle this case, as currently jwt will not add user details to security context
                        .description(product.getDescription())
                        .discountPercent(discount)
                        .originalPrice(product.getPrice())
                        .build()
                );
            }

            collection.setProducts(productsResponse);
        }

    }
}
