package com.user_service.Controllers;

import com.user_service.dto.AddressDTO;
import com.user_service.dto.ApiResponse;
import com.user_service.dto.UsersDTO;
import com.user_service.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<AddressDTO>>> getAllUsers() {
        List<AddressDTO> users = addressService.getAddresses();
        ApiResponse<List<AddressDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), users, "Address fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> addUser(@Valid @PathVariable Long id,@Valid @RequestBody AddressDTO dto) {

        UsersDTO users = addressService.saveAddress(id, dto);
        ApiResponse<UsersDTO> response = new ApiResponse<>(HttpStatus.OK.value(), users, "Address saved successfully");
        return ResponseEntity.ok(response);
    }

}
