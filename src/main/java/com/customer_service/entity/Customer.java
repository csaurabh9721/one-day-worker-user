package com.customer_service.entity;

import com.customer_service.util.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {



    @Column(nullable = false, unique = true)
    private Long identityId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dob;

    private String profileImage;

    @Builder.Default
    private Boolean active = true;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<CustomerAddress> addresses = new ArrayList<>();
}
