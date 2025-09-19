package edu.demo.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LocalAuthApi implements AuthApi {
    private final RestClient restClient;

    public LocalAuthApi() {
        this.restClient = RestClient.create();
    }

    @Override
    public User validateUser(String token) {
        return restClient.get().uri("http://localhost:8081/api/auth/validate/" + token).retrieve().body(User.class);
    }
}
