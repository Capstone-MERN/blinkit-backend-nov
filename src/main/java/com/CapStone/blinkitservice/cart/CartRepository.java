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

    public List<CartItemEntity> findByUserEntity (UserEntity userEntity);

    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE user_id = :userId AND product_id NOT IN (:productIds)", nativeQuery = true)
    public void removeNonExistingProductsFromCart(@Param("userId") int userId, @Param("productIds") List<Integer> productIds);

    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE user_id = :userId", nativeQuery = true)
    public void deleteAllByUserId(@Param("userId") int userId);

    @Query(value = "SELECT * FROM cart_items WHERE user_id =:userId", nativeQuery = true)
    public List<CartItemEntity> getCartItemByUserId(@Param("userId") int userId);
}
