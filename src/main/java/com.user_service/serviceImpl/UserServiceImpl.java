package com.user_service.serviceImpl;

import com.user_service.dto.AddressDTO;
import com.user_service.dto.UserDTO;
import com.user_service.entity.Address;
import com.user_service.entity.User;
import com.user_service.globleException.ResourceNotFound;
import com.user_service.repository.UserRepository;
import com.user_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = repository.findAll();
        return users.stream().map(this::convertToDto).toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found with id :" + id));
        return convertToDto(user);
    }

    @Override
    public UserDTO adduser(UserDTO dto) {
        User user = convertToEntity(dto);
        User savedUser = repository.save(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id :" + id));

        existingUser.setName(dto.getName());
        existingUser.setAge(dto.getAge());
        existingUser.setMobile(dto.getMobile());
        existingUser.setPhoto(dto.getPhoto());
        existingUser.setAddress(existingUser.getAddress());
        User savedUser = repository.save(existingUser);
        return convertToDto(savedUser);
    }

    @Override
    public boolean deleteUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found with id :" + id));
        repository.deleteById(id);
        return true;
    }

    public User convertToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDTO convertToDto1(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setName(entity.getName());
        userDTO.setAge(entity.getAge());
        userDTO.setMobile(entity.getMobile());
        userDTO.setPhoto(entity.getPhoto());

        if (entity.getAddress() != null) {
            AddressDTO address = getAddressDTO(entity);
            userDTO.setAddress(address);
        }
        return userDTO;
    }

    private static AddressDTO getAddressDTO(User entity) {
        AddressDTO address = new AddressDTO();
        address.setId(entity.getAddress().getId());
        address.setCity(entity.getAddress().getCity());
        address.setState(entity.getAddress().getState());
        address.setCountry(entity.getAddress().getCountry());
        address.setLocality(entity.getAddress().getLocality());
        address.setSubLocality(entity.getAddress().getSubLocality());
        address.setStreetName(entity.getAddress().getStreetName());
        address.setPinCode(entity.getAddress().getPinCode());
        return address;
    }

    public UserDTO convertToDto(User userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
