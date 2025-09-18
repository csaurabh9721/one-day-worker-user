package com.user_service.service;

import com.user_service.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUserById(Long id);

    UserDTO adduser(UserDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    boolean deleteUserById(Long id);

}
