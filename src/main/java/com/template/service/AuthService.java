package com.template.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.template.dto.AuthResponse;
import com.template.dto.LoginRequest;
import com.template.dto.RegisterRequest;
import com.template.model.CustomUserDetails;
import com.template.model.User;
import com.template.model.User.Role;
import com.template.repository.UserRepository;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    @Autowired private AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
    	
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, "User with this email already exists.");
        }

        // Create and save new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);

        userRepository.save(user);
//
//        // Generate token
//        CustomUserDetails userDetails = new CustomUserDetails(user);
//        String token = jwtService.generateToken(userDetails);

        return new AuthResponse("Success", "User registered successfully.");
        
    }

    public AuthResponse login(LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    	CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
