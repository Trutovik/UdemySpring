package com.sabal.spring5webapp.service.impl;

import com.sabal.spring5webapp.io.entity.AddressEntity;
import com.sabal.spring5webapp.io.entity.UserEntity;
import com.sabal.spring5webapp.io.repositories.AddressRepository;
import com.sabal.spring5webapp.io.repositories.UserRepository;
import com.sabal.spring5webapp.service.AddressService;
import com.sabal.spring5webapp.shared.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddresses(String userId) {
        List<AddressDto> returnValue = new ArrayList<>();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) {
            return returnValue;
        }
        Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
        ModelMapper modelMapper = new ModelMapper();
        for (AddressEntity address : addresses) {
            returnValue.add(modelMapper.map(address, AddressDto.class));
        }
        return returnValue;
    }

    @Override
    public AddressDto getAddress(String addressId) {
        AddressDto returnValue = null;

        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

        if(addressEntity != null) {
            returnValue = new ModelMapper().map(addressEntity, AddressDto.class);
        }
        return returnValue;
    }
}
