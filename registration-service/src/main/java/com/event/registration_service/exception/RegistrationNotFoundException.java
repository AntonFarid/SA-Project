package com.event.registration_service.exception;

public class RegistrationNotFoundException extends RuntimeException{
    public RegistrationNotFoundException(Long id){
        super("Registration not found with ID: " + id);
    }
}
