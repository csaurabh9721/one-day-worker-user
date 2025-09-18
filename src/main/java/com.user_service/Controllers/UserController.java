package com.user_service.Controllers;

import com.user_service.dto.ApiResponse;
import com.user_service.dto.UserDTO;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userService/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), user, "User fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getUsers();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), users, "User fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/userRegister")
    public ResponseEntity<ApiResponse<UserDTO>> userRegister(@Valid @RequestBody UserDTO dto) {
        UserDTO user = userService.adduser(dto);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), user, "User saved successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateUserByUserId/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUserByUserId(@Valid @PathVariable Long id, @RequestBody UserDTO dto) {
        UserDTO user = userService.updateUser(id, dto);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK.value(), user, "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteUserByUserID/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUserByUserID(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        ApiResponse<Boolean> response = new ApiResponse<>(HttpStatus.OK.value(), deleted, "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
