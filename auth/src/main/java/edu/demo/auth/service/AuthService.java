package edu.demo.auth.service;

import edu.demo.auth.domain.User;
import edu.demo.auth.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository users;
    private Map<String, String> tokenStore = new HashMap<>();

    public AuthService(UserRepository users) { this.users = users; }

    public User register(String username, String password) {
        if (users.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        return users.save(User.builder().username(username).password(password).build());
    }

    public String login(String username, String password) {
        var user = users.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        String token = "demo-" + UUID.randomUUID();
        tokenStore.put(token, username);
        return token;
    }

    public String validate(String token) {
        return tokenStore.get(token);
    }

    public Long idForUsername(String username) {
        return users.findByUsername(username).orElseThrow().getId();
    }
}
