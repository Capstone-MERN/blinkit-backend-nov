package com.CapStone.blinkitservice.user.dto;

import com.CapStone.blinkitservice.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressRequest {
    float latitude;

    Integer addressId;

    float longitude;

    String addressLine1;

    String addressLine2;

    String addressLine3;

    String phone_no;
}
