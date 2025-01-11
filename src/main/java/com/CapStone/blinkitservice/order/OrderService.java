package com.CapStone.blinkitservice.order;

import com.CapStone.blinkitservice.cart.CartRepository;
import com.CapStone.blinkitservice.cart.entity.CartItemEntity;
import com.CapStone.blinkitservice.common.StringConstants;
import com.CapStone.blinkitservice.controlleradvice.exceptions.BadRequestException;
import com.CapStone.blinkitservice.order.entity.OrderEntity;
import com.CapStone.blinkitservice.order.entity.OrderItemEntity;
import com.CapStone.blinkitservice.order.enums.DeliveryStatus;
import com.CapStone.blinkitservice.order.model.OrderRequest;
import com.CapStone.blinkitservice.order.model.OrderResponse;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.user.repository.AddressBookRepository;
import com.CapStone.blinkitservice.user.Service.AddressService;
import com.CapStone.blinkitservice.user.repository.UserRepository;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressService addressService;


    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest,String email){

        UserEntity userEntity=userRepository.findByEmail(email);

        if(orderRequest.getAddressId()==null){
            throw new BadRequestException("AddressId can not be null");
        }

        AddressBookEntity addressBookEntity=addressService.validateUserVsAddress(userEntity,orderRequest.getAddressId());

        List<CartItemEntity> cartItems=cartRepository.findByUserEntity(userEntity);

        if(cartItems.isEmpty()){
            throw new BadRequestException("Sorry,Unable to placing order because your cart is empty");
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

        List<OrderItemEntity> orderItemEntities= generateOrder(cartItems,orderEntity);

        orderItemRepository.saveAll(orderItemEntities);

        cartRepository.deleteAllByUserId(userEntity.getId());

        return OrderResponse.builder()
                .orderId(orderEntity.getId())
                .message(StringConstants.ORDER_STATUS).build();
    }

    private List<OrderItemEntity> generateOrder(List<CartItemEntity> cartItems,OrderEntity orderEntity){

        List<OrderItemEntity> orderItemEntities=new ArrayList<>();

        float totalAmount=0.0f;
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

        orderEntity.setTotalAmountPaid(amountWithDiscount);
        orderEntity.setAmountSaved(totalAmount-amountWithDiscount);

        return orderItemEntities;

    }
}
