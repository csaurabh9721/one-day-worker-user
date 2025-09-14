package com.user_service.serviceImpl;

import com.user_service.dto.UsersDTO;
import com.user_service.entity.Users;
import com.user_service.globleException.UserNotFoundException;
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
    public List<UsersDTO> getUsers() {
        List<Users> users = repository.findAll();
        return users.stream().map(this::convertToDto).toList();
    }

    @Override
    public UsersDTO getUserById(Long id) {
        Users user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User", id));
        return convertToDto(user);
    }

    @Override
    public UsersDTO adduser(UsersDTO dto) {
        Users users = convertToEntity(dto);
        Users savedUser = repository.save(users);
        return convertToDto(savedUser);
    }

    @Override
    public UsersDTO updateUser(Long id, UsersDTO dto) {
        Users existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User", id));

        existingUser.setName(dto.getName());
        existingUser.setAge(dto.getAge());
        existingUser.setMobile(dto.getMobile());
        existingUser.setPhoto(dto.getPhoto());
        existingUser.setAddress(existingUser.getAddress());
        Users savedUser = repository.save(existingUser);
        return convertToDto(savedUser);
    }

    @Override
    public boolean deleteUserById(Long id) {
        Users user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User", id));
        repository.deleteById(id);
        return true;
    }

    public UsersDTO convertToDto(Users userEntity) {
        return modelMapper.map(userEntity, UsersDTO.class);
    }

    public Users convertToEntity(UsersDTO userDto) {
        return modelMapper.map(userDto, Users.class);
    }

}
