package com.suyash.support_platform.controller;

import com.suyash.support_platform.config.JwtUtil;
import com.suyash.support_platform.dto.LoginRequest;
import com.suyash.support_platform.dto.RegisterRequest;
import com.suyash.support_platform.model.User;
import com.suyash.support_platform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwt;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository repo,JwtUtil jwt,PasswordEncoder encoder){
        this.repo=repo;
        this.jwt=jwt;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req){
        User user = repo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(req.getPassword(), user.getPassword()))
        {
            throw new RuntimeException("Bad credentials");
        }
        return jwt.generate(user.getUsername(), user.getRole());
    }

    @GetMapping("/login-test")
    public String test() {
        return "login mapping exists";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        if (repo.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("User exists");
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRole(req.getRole());

        repo.save(u);

        return "Registered";
    }



}
