package com.customer_service.service;

import com.customer_service.dto.CustomerAddressRequestDto;
import com.customer_service.dto.CustomerAddressResponseDto;

import java.util.List;

public interface CustomerAddressService {
    CustomerAddressResponseDto saveAddress(Long customerID, CustomerAddressRequestDto requestDto);
        CustomerAddressResponseDto updateAddress(Long customerID, Long addressID, CustomerAddressRequestDto requestDto);
    List<CustomerAddressResponseDto> getAddressByUserId(Long customerID);
}
