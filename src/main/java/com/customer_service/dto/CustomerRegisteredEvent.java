package com.customer_service.dto;

public record CustomerRegisteredEvent(
        Long eventId,
        Long identityId,
        Long customerId,
        String email,
        String phone,
        String firstName,
        String lastName
) {
}