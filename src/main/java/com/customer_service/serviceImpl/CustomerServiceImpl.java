package com.customer_service.serviceImpl;

import com.customer_service.AppConfiguration.RabbitMQConfig;
import com.customer_service.dto.*;
import com.customer_service.entity.Customer;
import com.customer_service.globleException.DuplicateRequestContentException;
import com.customer_service.globleException.ResourceNotFound;
import com.customer_service.repository.CustomerRepository;
import com.customer_service.service.AuthFeignClient;
import com.customer_service.service.CustomerService;
import com.customer_service.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;
    private final AuthFeignClient authFeignClient;

    @Override
    public List<CustomerResponseDto> getCustomers() {
        List<Customer> customers = repository.findAll();
        return customers.stream().map(this::convertToDto).toList();
    }

    @Override
    public CustomerDetailResponse getCustomerDetail() {
        Long id =  SecurityUtil.getCurrentUserId();
        Customer customer = repository.findByIdentityId(id).orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + id));

        IdentityDto identity = authFeignClient.authMe();
        return CustomerDetailResponse.builder()
                .email(identity.getEmail())
                .emailVerified(identity.getEmailVerified())
                .phoneVerified(identity.getPhoneVerified())
                .id(customer.getId())
                .identityId(customer.getIdentityId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .gender(customer.getGender())
                .dob(customer.getDob())
                .profileImage(customer.getProfileImage())
                .active(customer.getActive())
                .build();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
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
        CustomerRegisteredEvent event =
                new CustomerRegisteredEvent(
                        savedCustomer.getIdentityId(),
                        savedCustomer.getId(),
                        "Dummy-Email",
                        savedCustomer.getPhone(),
                        savedCustomer.getFirstName(),
                        savedCustomer.getLastName()
                );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
            return convertToDto(savedCustomer);

    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) {
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
    public boolean deleteCustomerById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Customer not found with id :" + id));
        repository.deleteById(id);
        return true;
    }

    public Customer convertToEntity(CustomerRequestDto dto) {
        return Customer.builder()
                .identityId(dto.getIdentityId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .profileImage(dto.getProfileImage())
                .build();
    }

    public CustomerResponseDto convertToDto(Customer entity) {
        return modelMapper.map(entity, CustomerResponseDto.class);
    }

}
