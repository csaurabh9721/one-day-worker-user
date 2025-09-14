package com.user_service.serviceImpl;

import com.user_service.dto.AddressDTO;
import com.user_service.dto.UsersDTO;
import com.user_service.entity.Address;
import com.user_service.entity.Users;
import com.user_service.globleException.UserNotFoundException;
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
    @Transactional
    public UsersDTO saveAddress(Long userId, AddressDTO dto) {
        Users userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User", userId));
        if(userEntity.getAddress() == null){
            Address addressEntity = convertToEntity(dto);
            addressEntity.setUsers(userEntity);
            Address savedAddress = repository.save(addressEntity);
            userEntity.setAddress(savedAddress);
            userRepository.save(userEntity);
        }else {
            Address addressEntity = userEntity.getAddress();
            addressEntity.setStreetName(dto.getStreetName());
            addressEntity.setSublocality(dto.getSublocality());
            addressEntity.setLocality(dto.getLocality());
            addressEntity.setCity(dto.getCity());
            addressEntity.setState(dto.getState());
            addressEntity.setCountry(dto.getCountry());
            addressEntity.setPinCode(dto.getPinCode());
            Address savedAddress = repository.save(addressEntity);
            userEntity.setAddress(savedAddress);
            userRepository.save(userEntity);
        }

        return modelMapper.map(userEntity, UsersDTO.class);
    }



    public AddressDTO convertToDto(Address userEntity) {
        return modelMapper.map(userEntity, AddressDTO.class);
    }

    public Address convertToEntity(AddressDTO userDto) {
        return modelMapper.map(userDto, Address.class);
    }

}
