package com.user_service.Controllers;

import com.user_service.dto.AddressDTO;
import com.user_service.dto.ApiResponse;
import com.user_service.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userService/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @GetMapping("/getAllAddress")
    public ResponseEntity<ApiResponse<List<AddressDTO>>> getAllAddress() {
        List<AddressDTO> user = addressService.getAddresses();
        ApiResponse<List<AddressDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), user, "Address fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addAddressByUser/{id}")
    public ResponseEntity<ApiResponse<AddressDTO>> addAddressByUser(@PathVariable Long id,@Valid @RequestBody AddressDTO dto) {
        AddressDTO user = addressService.saveAddress(id, dto);
        ApiResponse<AddressDTO> response = new ApiResponse<>(HttpStatus.OK.value(), user, "Address saved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAddressByUserId/{id}")
    public ResponseEntity<ApiResponse<AddressDTO>> getAddressByUserId(@PathVariable Long id) {
        AddressDTO user = addressService.getAddressByUserId(id);
        ApiResponse<AddressDTO> response = new ApiResponse<>(HttpStatus.OK.value(), user, "Address fetched successfully");
        return ResponseEntity.ok(response);
    }

}
