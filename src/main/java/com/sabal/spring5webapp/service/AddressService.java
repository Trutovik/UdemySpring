package com.sabal.spring5webapp.service;

import com.sabal.spring5webapp.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAddresses(String userId);
    AddressDto getAddress(String addressId);
}
