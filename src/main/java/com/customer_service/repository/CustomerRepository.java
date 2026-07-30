package com.customer_service.repository;

import com.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByIdentityId(Long identityId);
    Optional<Customer> findByIdentityId(Long identityId);
}
