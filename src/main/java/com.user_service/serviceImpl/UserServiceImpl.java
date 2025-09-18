package com.user_service.serviceImpl;

import com.user_service.dto.UserDTO;
import com.user_service.entity.User;
import com.user_service.globleException.ResourceNotFound;
import com.user_service.repository.UserRepository;
import com.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserDTO convertToDto(User userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
