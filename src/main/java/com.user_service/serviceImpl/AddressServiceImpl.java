package com.user_service.serviceImpl;

import com.user_service.dto.AddressDTO;
import com.user_service.entity.Address;
import com.user_service.entity.Users;
import com.user_service.globleException.ResourceNotFound;
import com.user_service.repository.AddressRepository;
import com.user_service.repository.UserRepository;
import com.user_service.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository repository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> users = repository.findAll();
        return users.stream().map(this::convertToDto).toList();
    }

    @Override
    public AddressDTO getAddressByUserId(Long userId) {
        Users userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id :" +userId));
        Address addressEntity = userEntity.getAddress();
        if (addressEntity == null) {
            throw new ResourceNotFound("Address not found with id :" +userId);
        }
        return convertToDto(userEntity.getAddress());
    }


    @Override
    @Transactional
    public AddressDTO saveAddress(Long userId, AddressDTO dto) {
        Users userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id :" +userId));
        Address addressEntity = userEntity.getAddress();
        if (addressEntity == null) {
            addressEntity = convertToEntity(dto);
            addressEntity.setUsers(userEntity);
        } else {
            Long existingId = addressEntity.getId();
            modelMapper.map(dto, addressEntity);
            addressEntity.setId(existingId);
        }
        Address savedAddress = repository.save(addressEntity);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }


    public AddressDTO convertToDto(Address userEntity) {
        return modelMapper.map(userEntity, AddressDTO.class);
    }

    public Address convertToEntity(AddressDTO userDto) {
        return modelMapper.map(userDto, Address.class);
    }

}
