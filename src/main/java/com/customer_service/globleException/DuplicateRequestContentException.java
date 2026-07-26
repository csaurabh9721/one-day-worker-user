package com.customer_service.globleException;

public class DuplicateRequestContentException extends RuntimeException {
    public DuplicateRequestContentException(String message) {
        super(message);
    }
}
