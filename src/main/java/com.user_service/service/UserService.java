package com.user_service.service;

import com.user_service.dto.UsersDTO;

import java.util.List;

public interface UserService {
    List<UsersDTO> getUsers();

    UsersDTO getUserById(Long id);

    UsersDTO adduser(UsersDTO dto);

    UsersDTO updateUser(Long id,UsersDTO dto);

    boolean deleteUserById(Long id);

}
