package com.notes.thinknotesbackend.controller;

import com.notes.thinknotesbackend.config.security.util.request.LoginRequest;
import com.notes.thinknotesbackend.config.security.util.request.SignUpRequest;
import com.notes.thinknotesbackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    private AuthService authService;

    @PostMapping("/public/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/public/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return authService.getUserDetails(userDetails);

    }
    @GetMapping("/username")
    public String getUserame(@AuthenticationPrincipal UserDetails userDetails) {

        return authService.getUserName(userDetails);
    }

}