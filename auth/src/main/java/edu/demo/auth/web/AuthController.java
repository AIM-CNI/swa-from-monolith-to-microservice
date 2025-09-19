package edu.demo.auth.web;

import edu.demo.auth.service.AuthService;
import edu.demo.auth.web.dto.LoginRequest;
import edu.demo.auth.web.dto.LoginResponse;
import edu.demo.auth.web.dto.RegisterRequest;
import edu.demo.auth.web.dto.ValidateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        var u = auth.register(req.username(), req.password());
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        String token = auth.login(req.username(), req.password());
        Long userId = auth.idForUsername(req.username());
        return ResponseEntity.ok(new LoginResponse(token, userId));
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<ValidateResponse> validate(@PathVariable("token") String token) {
        String userName = auth.validate(token);
        return ResponseEntity.ok(new ValidateResponse(auth.idForUsername(userName), userName));
    }
}
