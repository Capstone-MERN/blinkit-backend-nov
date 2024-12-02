package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItemEntity, Integer> {

    public CartItemEntity findByUserEntityAndProductEntity (UserEntity userEntity, ProductEntity productEntity);

    public List<CartItemEntity> findByUserEntity (UserEntity userEntity);

    @Query(value = "select * from cart_items where user_id =:userId and product_id =:productId",nativeQuery = true)
    public CartItemEntity findByProductIdAndUserId(int productId, int userId);

    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE user_id = :userId AND product_id NOT IN (:productIds)", nativeQuery = true)
    public void deleteInvalidProductsByUserIdAndProductIds(@Param("userId") int userId, @Param("productIds") List<Integer> productIds);
}
