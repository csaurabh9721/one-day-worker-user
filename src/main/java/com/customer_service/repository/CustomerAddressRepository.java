package com.customer_service.repository;
import com.customer_service.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    Optional<CustomerAddress> findByIdAndCustomerId(Long addressId, Long customerId);
}
