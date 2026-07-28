package com.customer_service.entity;

import com.customer_service.util.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType addressType;

    @Column(nullable = false)
    private String houseNo;

    private String street;

    private String landmark;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, length = 10)
    private String pincode;

    private Double latitude;

    private Double longitude;

    @Builder.Default
    private Boolean defaultAddress = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
