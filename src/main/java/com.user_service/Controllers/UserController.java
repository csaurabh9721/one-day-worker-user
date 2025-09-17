package com.user_service.Controllers;

import com.user_service.dto.ApiResponse;
import com.user_service.dto.UsersDTO;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userApi/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> getUserById(@PathVariable Long id) {
        UsersDTO users = userService.getUserById(id);
        ApiResponse<UsersDTO> response = new ApiResponse<>(HttpStatus.OK.value(), users, "User fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<List<UsersDTO>>> getAllUsers() {
        List<UsersDTO> users = userService.getUsers();
        ApiResponse<List<UsersDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), users, "User fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/userRegister")
    public ResponseEntity<ApiResponse<UsersDTO>> userRegister(@Valid @RequestBody UsersDTO dto) {
        UsersDTO users = userService.adduser(dto);
        ApiResponse<UsersDTO> response = new ApiResponse<>(HttpStatus.OK.value(), users, "User saved successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateUserByUserId/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> updateUserByUserId(@Valid @PathVariable Long id, @RequestBody UsersDTO dto) {
        UsersDTO users = userService.updateUser(id, dto);
        ApiResponse<UsersDTO> response = new ApiResponse<>(HttpStatus.OK.value(), users, "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteUserByUserID/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUserByUserID(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        ApiResponse<Boolean> response = new ApiResponse<>(HttpStatus.OK.value(), deleted, "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
