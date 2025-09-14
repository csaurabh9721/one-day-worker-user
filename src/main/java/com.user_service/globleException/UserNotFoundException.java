package com.user_service.globleException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String header, Long id) {
        super(header + " not found with ID " + id);
    }

}
