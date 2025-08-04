package com.kpmg.parkingreservation.controller;

import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.dto.request.UserLoginRequest;
import com.kpmg.parkingreservation.security.jwt.JwtResponse;
import com.kpmg.parkingreservation.security.jwt.JwtUtil;
import com.kpmg.parkingreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        User user = userService.getByUsername(request.getUserName());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}