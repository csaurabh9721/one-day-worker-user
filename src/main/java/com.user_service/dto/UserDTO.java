package com.user_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be 3–50 characters")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    private String photo;

    @NotBlank(message = "Mobile number is required")
    @Size(min = 5, max = 20, message = "Mobile must be 5–20 characters")
    private String mobile;

    private AddressDTO address;
}
