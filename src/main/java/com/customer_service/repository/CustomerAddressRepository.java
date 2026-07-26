package com.customer_service.repository;
import com.customer_service.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {
    Optional<CustomerAddress> findByIdAndCustomerId(UUID addressId, UUID customerId);
}
