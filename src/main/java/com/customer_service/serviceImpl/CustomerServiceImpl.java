package com.customer_service.serviceImpl;

import com.customer_service.dto.CustomerRequestDto;
import com.customer_service.dto.CustomerResponseDto;
import com.customer_service.entity.Customer;
import com.customer_service.globleException.DuplicateRequestContentException;
import com.customer_service.globleException.ResourceNotFound;
import com.customer_service.repository.CustomerRepository;
import com.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerResponseDto> getCustomers() {
        List<Customer> customers = repository.findAll();
        return customers.stream().map(this::convertToDto).toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + id));
        return convertToDto(customer);
    }

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto dto) {
        if(repository.existsByIdentityId(dto.getIdentityId())){
            throw new DuplicateRequestContentException("Customer with identity ID " + dto.getIdentityId() + " already exists");
        }
        Customer customer = convertToEntity(dto);
        Customer savedCustomer = repository.save(customer);
        return convertToDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto dto) {
        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + id));
        existingCustomer.setFirstName(dto.getFirstName());
        existingCustomer.setLastName(dto.getLastName());
        existingCustomer.setPhone(dto.getPhone());
        existingCustomer.setProfileImage(dto.getProfileImage());
        existingCustomer.setGender(dto.getGender());
        existingCustomer.setDob(dto.getDob());
        Customer savedCustomer = repository.save(existingCustomer);
        return convertToDto(savedCustomer);
    }

    @Override
    public boolean deleteCustomerById(UUID id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + id));
        repository.deleteById(id);
        return true;
    }

    public Customer convertToEntity(CustomerRequestDto userDto) {
        return modelMapper.map(userDto, Customer.class);
    }

    public CustomerResponseDto convertToDto(Customer entity) {
        return modelMapper.map(entity, CustomerResponseDto.class);
    }

}
