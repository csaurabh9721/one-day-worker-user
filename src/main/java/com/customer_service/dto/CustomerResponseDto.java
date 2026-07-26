package com.customer_service.dto;

import com.customer_service.util.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {

    private UUID id;

    private UUID identityId;

    private String firstName;

    private String lastName;

    private String phone;

    private Gender gender;

    private LocalDate dob;

    private String profileImage;

    private Boolean active ;

    private List<CustomerAddressResponseDto> addresses;
}
