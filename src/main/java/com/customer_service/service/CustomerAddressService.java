package com.customer_service.service;

import com.customer_service.dto.CustomerAddressRequestDto;
import com.customer_service.dto.CustomerAddressResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressService {
    CustomerAddressResponseDto saveAddress(UUID customerID, CustomerAddressRequestDto requestDto);
    CustomerAddressResponseDto updateAddress(UUID customerID, UUID addressID, CustomerAddressRequestDto requestDto);
    List<CustomerAddressResponseDto> getAddressByUserId(UUID customerID);
}
