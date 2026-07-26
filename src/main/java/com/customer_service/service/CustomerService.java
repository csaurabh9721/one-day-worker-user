package com.customer_service.service;

import com.customer_service.dto.CustomerRequestDto;
import com.customer_service.dto.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerResponseDto> getCustomers();

    CustomerResponseDto getCustomerById(UUID id);

    CustomerResponseDto addCustomer(CustomerRequestDto dto);

    CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto dto);

    boolean deleteCustomerById(UUID id);

}
