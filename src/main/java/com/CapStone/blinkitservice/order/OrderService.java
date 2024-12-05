package com.CapStone.blinkitservice.order;

import com.CapStone.blinkitservice.cart.CartRepository;
import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.common.StringConstants;
import com.CapStone.blinkitservice.order.entity.OrderEntity;
import com.CapStone.blinkitservice.order.entity.OrderItemEntity;
import com.CapStone.blinkitservice.order.enums.DeliveryStatus;
import com.CapStone.blinkitservice.order.orderDTO.OrderRequest;
import com.CapStone.blinkitservice.order.orderDTO.OrderResponse;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.AddressBookRepository;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest,String email){

        UserEntity userEntity=userRepository.findByEmail(email);

        Optional<AddressBookEntity> addressBookEntityResult=addressBookRepository.findById(orderRequest.getAddressId());

        if(addressBookEntityResult.isEmpty()){
            throw new RuntimeException("Invalid addressId "+orderRequest.getAddressId());
        }

        AddressBookEntity addressBookEntity=addressBookEntityResult.get();

        List<CartItemEntity> cartItems=cartRepository.findByUserEntity(userEntity);

        if(cartItems.isEmpty()){
            throw new RuntimeException("Sorry,Unable to order because your cart is empty");
        }

        OrderEntity orderEntity=OrderEntity.builder()
                .addressBookEntity(addressBookEntity)
                .orderedLocationLatitude(addressBookEntity.getLatitude())
                .orderedLocationLongitude(addressBookEntity.getLongitude())
                .userEntity(userEntity)
                .contactNumber(orderRequest.getContactNumber())
                .timestamp(orderRequest.getTimestamp())
                .deliveryStatus(DeliveryStatus.PENDING)
                .build();

        generateOrder(cartItems,orderEntity);

        orderRepository.save(orderEntity);

        cartRepository.deleteAllByUserId(userEntity.getId());

        return OrderResponse.builder()
                .orderId(orderEntity.getId())
                .message(StringConstants.ORDER_STATUS).build();
    }

    private void generateOrder(List<CartItemEntity> cartItems,OrderEntity orderEntity){

        List<OrderItemEntity> orderItemEntities=new ArrayList<>();

        Float totalAmount=0.0f;
        float amountWithDiscount=0.0f;

        for (CartItemEntity cartItem:cartItems){

            ProductEntity productEntity=cartItem.getProductEntity();

            totalAmount+=productEntity.getPrice()*cartItem.getQuantity();

            Float dicountApplied=(productEntity.getDiscount()!=null)?productEntity.getDiscount():0;
            amountWithDiscount+=(productEntity.getPrice()-productEntity.getPrice()*dicountApplied/100)*cartItem.getQuantity();

            OrderItemEntity orderItemEntity= OrderItemEntity.builder()
                    .orderEntity(orderEntity)
                    .amountPaid(productEntity.getPrice())
                    .discount(dicountApplied)
                    .productEntity(productEntity)
                    .quantity(cartItem.getQuantity())
                    .build();

            orderItemEntities.add(orderItemEntity);
        }

        orderEntity.setOrderItemEntities(orderItemEntities);
        orderEntity.setTotalAmountPaid(amountWithDiscount);
        orderEntity.setAmountSaved(totalAmount-amountWithDiscount);

    }
}
