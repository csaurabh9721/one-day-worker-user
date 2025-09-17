package com.user_service.service;

import com.user_service.dto.AddressDTO;
import com.user_service.dto.UsersDTO;

import java.util.List;

public interface AddressService {
    AddressDTO saveAddress(Long userId, AddressDTO addressDTO);
    List<AddressDTO> getAddresses();
    AddressDTO getAddressByUserId(Long userId);
}
