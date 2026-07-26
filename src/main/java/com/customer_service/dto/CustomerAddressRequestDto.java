package com.customer_service.dto;

import com.customer_service.util.enums.AddressType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddressRequestDto {

    @NotBlank(message = "Address type is required")
    private AddressType addressType;

    @NotBlank(message = "House number is required")
    private String houseNo;

    @NotBlank(message = "Street is required")
    private String street;

    private String landmark;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @Min(value = 100000, message = "PinCode must be at least 6 digits")
    @Max(value = 999999, message = "PinCode cannot exceed 6 digits")
    private String pincode;

    private Double latitude;

    private Double longitude;

    private Boolean defaultAddress;

}
