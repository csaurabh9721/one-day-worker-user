package com.customer_service.dto;

import com.customer_service.util.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {

    private UUID identityId;

    private String firstName;

    private String lastName;

    private String phone;

    private Gender gender;

    private LocalDate dob;

    private String profileImage;

}
