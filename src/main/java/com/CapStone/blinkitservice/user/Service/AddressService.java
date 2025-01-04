package com.CapStone.blinkitservice.user.Service;

import com.CapStone.blinkitservice.user.Repository.AddressRepository;
import com.CapStone.blinkitservice.user.Transformer.AddressTransformer;
import com.CapStone.blinkitservice.user.UserRepository;
import com.CapStone.blinkitservice.user.dto.AddressRequest;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressBookEntity addOrUpdateAddress(String email, AddressRequest addressRequest) {
        UserEntity user = userRepository.findByEmail(email);
        Integer addressId = addressRequest.getAddressId();

        return (addressId != null)
                ? updateAddress(addressId, addressRequest)
                : createAddress(user, addressRequest);
    }

    private AddressBookEntity updateAddress(Integer addressId, AddressRequest addressRequest) {
        AddressBookEntity address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Invalid address ID"));

        return saveAddress(updateAddressDetails(address, addressRequest));
    }

    private AddressBookEntity updateAddressDetails(AddressBookEntity address, AddressRequest addressRequest) {
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setAddressLine3(addressRequest.getAddressLine3());
        address.setLatitude(addressRequest.getLatitude());
        address.setLongitude(addressRequest.getLongitude());
        address.setPhone_no(addressRequest.getPhone_no());
        return address;
    }

    private AddressBookEntity createAddress(UserEntity user, AddressRequest addressRequest) {
        AddressBookEntity address = AddressTransformer.addressRequestToAddress(addressRequest);

        if (user.getAddresses() == null) {
            user.setAddresses(new ArrayList<>());
        }
        address.setUserEntity(user);
        user.getAddresses().add(address);

        userRepository.save(user); // Save the user with the new address
        return saveAddress(address);
    }

    private AddressBookEntity saveAddress(AddressBookEntity address) {
        return addressRepository.save(address);
    }
}
