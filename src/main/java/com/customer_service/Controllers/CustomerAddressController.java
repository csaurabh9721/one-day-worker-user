package com.customer_service.Controllers;

import com.customer_service.dto.CustomerAddressRequestDto;
import com.customer_service.dto.CustomerAddressResponseDto;
import com.customer_service.dto.ApiResponse;
import com.customer_service.service.CustomerAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customerService/address")
@RequiredArgsConstructor
public class CustomerAddressController {
    private final CustomerAddressService addressService;


    @PostMapping("/addAddressByUser/{id}")
    public ResponseEntity<ApiResponse<CustomerAddressResponseDto>> addAddressByUser(@PathVariable UUID id, @Valid @RequestBody CustomerAddressRequestDto dto) {
        CustomerAddressResponseDto user = addressService.saveAddress(id, dto);
        ApiResponse<CustomerAddressResponseDto> response = new ApiResponse<>(HttpStatus.OK.value(), user, "Address saved successfully");
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateAddress/{customerId}/{addressId}")
    public ResponseEntity<ApiResponse<CustomerAddressResponseDto>> updateAddress(@PathVariable UUID customerId, @PathVariable UUID addressId, @Valid @RequestBody CustomerAddressRequestDto dto) {
        CustomerAddressResponseDto user = addressService.updateAddress(customerId, addressId, dto);
        ApiResponse<CustomerAddressResponseDto> response = new ApiResponse<>(HttpStatus.OK.value(), user, "Address updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAddressByUserId/{id}")
    public ResponseEntity<ApiResponse<List<CustomerAddressResponseDto>>> getAddressByUserId(@PathVariable UUID id) {
        List<CustomerAddressResponseDto> user = addressService.getAddressByUserId(id);
        ApiResponse<List<CustomerAddressResponseDto>> response = new ApiResponse<>(HttpStatus.OK.value(), user, "Address fetched successfully");
        return ResponseEntity.ok(response);
    }

}
