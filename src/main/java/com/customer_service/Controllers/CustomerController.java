package com.customer_service.Controllers;

import com.customer_service.dto.ApiResponse;
import com.customer_service.dto.CustomerRequestDto;
import com.customer_service.dto.CustomerResponseDto;
import com.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerService/customer")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto customer = customerService.getCustomerById(id);
        ApiResponse<CustomerResponseDto> response = new ApiResponse<>(HttpStatus.OK.value(), customer, "Customer fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getAllCustomers() {
        List<CustomerResponseDto> customers = customerService.getCustomers();
        ApiResponse<List<CustomerResponseDto>> response = new ApiResponse<>(HttpStatus.OK.value(), customers, "Customer fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customerRegister")
    public ResponseEntity<CustomerResponseDto> customerRegister(@Valid @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto customer = customerService.addCustomer(dto);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/updateCustomerByCustomerId/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> updateCustomerByCustomerId(@Valid @PathVariable Long id, @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto customer = customerService.updateCustomer(id, dto);
        ApiResponse<CustomerResponseDto> response = new ApiResponse<>(HttpStatus.OK.value(), customer, "Customer updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteCustomerByCustomerId/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCustomerByCustomerId(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomerById(id);
        ApiResponse<Boolean> response = new ApiResponse<>(HttpStatus.OK.value(), deleted, "Customer deleted successfully");
        return ResponseEntity.ok(response);
    }
}
