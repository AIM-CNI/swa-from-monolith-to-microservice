package edu.demo.auth.service;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String invalidCredentials) {
        super(invalidCredentials);
    }
}
