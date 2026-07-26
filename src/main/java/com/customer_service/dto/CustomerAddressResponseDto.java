package com.customer_service.dto;

import com.customer_service.util.enums.AddressType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddressResponseDto {

    private UUID id;

    private AddressType addressType;

    private String houseNo;

    private String street;

    private String landmark;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private Double latitude;

    private Double longitude;

    private Boolean defaultAddress;
}