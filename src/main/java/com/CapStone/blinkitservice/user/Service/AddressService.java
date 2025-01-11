package com.CapStone.blinkitservice.user.Service;

import com.CapStone.blinkitservice.controlleradvice.exceptions.BadRequestException;
import com.CapStone.blinkitservice.user.repository.AddressBookRepository;
import com.CapStone.blinkitservice.user.repository.UserRepository;
import com.CapStone.blinkitservice.user.model.AddressRequest;
import com.CapStone.blinkitservice.user.model.AddressResponse;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AddressService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressBookRepository addressBookRepository;

    public AddressResponse addOrUpdateAddress(String email, AddressRequest addressRequest) {

        Integer addressId = addressRequest.getAddressId();
        UserEntity userEntity=userRepository.findByEmail(email);

        AddressBookEntity addressBookEntity=validateUserVsAddress(userEntity,addressId);

        updateAddressDetails(addressBookEntity,addressRequest);

        addressBookRepository.save(addressBookEntity);
        return buildResponse(addressBookEntity);
    }

    public AddressBookEntity validateUserVsAddress(UserEntity userEntity,Integer addressId){

        if(addressId==null){
            return AddressBookEntity.builder()
                    .userEntity(userEntity).build();
        }
        Optional<AddressBookEntity> addressBookEntityResult=addressBookRepository.findById(addressId);

        if(addressBookEntityResult.isEmpty()){
            throw new BadRequestException("Invalid addressId "+addressId);
        }
        AddressBookEntity addressBookEntity=addressBookEntityResult.get();

        if(!addressBookEntity.getUserEntity().equals(userEntity)){
            throw new BadRequestException("The given address is not in your saved addresses");
        }
        return addressBookEntity;
    }

    private void updateAddressDetails(AddressBookEntity address, AddressRequest addressRequest) {
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setAddressLine3(addressRequest.getAddressLine3());
        address.setLatitude(addressRequest.getLatitude());
        address.setLongitude(addressRequest.getLongitude());
        address.setPhone_no(addressRequest.getPhoneNo());
    }

    private AddressResponse buildResponse(AddressBookEntity addressBookEntity){
        return AddressResponse.builder()
                .id(addressBookEntity.getId())
                .addressLine1(addressBookEntity.getAddressLine1())
                .addressLine2(addressBookEntity.getAddressLine2())
                .addressLine3(addressBookEntity.getAddressLine3())
                .longitude(addressBookEntity.getLongitude())
                .latitude(addressBookEntity.getLatitude())
                .build();
    }

}
