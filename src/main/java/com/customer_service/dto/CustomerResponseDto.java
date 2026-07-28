package com.customer_service.dto;

import com.customer_service.util.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {

    private Long id;

    private Long identityId;

    private String firstName;

    private String lastName;

    private String phone;

    private Gender gender;

    private LocalDate dob;

    private String profileImage;

    private Boolean active ;

    private List<CustomerAddressResponseDto> addresses;
}
