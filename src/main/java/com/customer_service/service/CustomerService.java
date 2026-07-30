package com.customer_service.service;

import com.customer_service.dto.CustomerDetailResponse;
import com.customer_service.dto.CustomerRequestDto;
import com.customer_service.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDto> getCustomers();

    CustomerDetailResponse getCustomerDetail();
    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto addCustomer(CustomerRequestDto dto);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto);

    boolean deleteCustomerById(Long id);

}
