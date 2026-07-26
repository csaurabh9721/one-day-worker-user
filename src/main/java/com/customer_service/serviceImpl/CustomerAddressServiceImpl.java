package com.customer_service.serviceImpl;

import com.customer_service.dto.CustomerAddressRequestDto;
import com.customer_service.dto.CustomerAddressResponseDto;
import com.customer_service.entity.Customer;
import com.customer_service.entity.CustomerAddress;
import com.customer_service.globleException.ResourceNotFound;
import com.customer_service.repository.CustomerAddressRepository;
import com.customer_service.repository.CustomerRepository;
import com.customer_service.service.CustomerAddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerAddressServiceImpl implements CustomerAddressService {
    private final CustomerAddressRepository repository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerAddressResponseDto> getAddressByUserId(UUID userId) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + userId));
        List<CustomerAddress> addressEntity = customer.getAddresses();
        if (addressEntity.isEmpty()) {
            throw new ResourceNotFound("Address not found with id :" + userId);
        }
        return addressEntity.stream().map(this::convertToDto).toList();
    }


    @Override
    @Transactional
    public CustomerAddressResponseDto saveAddress(UUID customerId, CustomerAddressRequestDto dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + customerId));
        CustomerAddress addressEntity = convertToEntity(dto);
        addressEntity.setCustomer(customer);
        CustomerAddress savedAddress = repository.save(addressEntity);
        return modelMapper.map(savedAddress, CustomerAddressResponseDto.class);
    }

    @Override
    public CustomerAddressResponseDto updateAddress(UUID customerId, UUID addressId, CustomerAddressRequestDto requestDto) {

        CustomerAddress addressEntity = repository
                .findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(() -> new ResourceNotFound("Address not found with id :" + addressId));
        addressEntity.setAddressType(requestDto.getAddressType());
        addressEntity.setHouseNo(requestDto.getHouseNo());
        addressEntity.setStreet(requestDto.getStreet());
        addressEntity.setCity(requestDto.getCity());
        addressEntity.setLandmark(requestDto.getLandmark());
        addressEntity.setCountry(requestDto.getCountry());
        addressEntity.setPincode(requestDto.getPincode());
        addressEntity.setState(requestDto.getState());
        addressEntity.setLatitude(requestDto.getLatitude());
        addressEntity.setLongitude(requestDto.getLongitude());
        addressEntity.setDefaultAddress(requestDto.getDefaultAddress());
        CustomerAddress updatedAddress = repository.save(addressEntity);
        return modelMapper.map(updatedAddress, CustomerAddressResponseDto.class);
    }


    public CustomerAddressResponseDto convertToDto(CustomerAddress entity) {
        return modelMapper.map(entity, CustomerAddressResponseDto.class);
    }

    public CustomerAddress convertToEntity(CustomerAddressRequestDto dto) {
        return modelMapper.map(dto, CustomerAddress.class);
    }

}
