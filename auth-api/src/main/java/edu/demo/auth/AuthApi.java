package edu.demo.auth;

public interface AuthApi {
    User validateUser(String token);
}
