package com.example.school_management_system.controller;

import com.example.school_management_system.config.JwtUtils;
import com.example.school_management_system.dto.request.CreateUserDTO;
import com.example.school_management_system.dto.response.LoginUserDTO;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.UserService;
import com.example.school_management_system.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public AuthController(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UserRepository userRepository, EmailService emailService) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    // User Registration
    @PostMapping("/register")
    public String register(@RequestBody CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole(createUserDTO.getRole());

        userRepository.save(user);
        return "User registered successfully";
    }


    // User Login
    @PostMapping("/login")
    public LoginUserDTO login(@Valid @RequestBody CreateUserDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtils.generateToken(user.getEmail(), List.of(user.getRole().name()));

        return new LoginUserDTO(token, user.getEmail(), user.getRole());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginUserDTO> refreshToken(@RequestHeader("Authorization") String oldToken) {
        String token = oldToken.replace("Bearer ", "");
        if (!jwtUtils.validateToken(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        String email = jwtUtils.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newToken = jwtUtils.generateToken(user.getEmail(), List.of(user.getRole().name()));

        return ResponseEntity.ok(new LoginUserDTO(newToken, user.getEmail(), user.getRole()));
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        String resetToken = UUID.randomUUID().toString(); // Generate a unique token
        user.setResetToken(resetToken); // تعيين رمز إعادة التعيين
        userRepository.save(user);

        // Send email with the reset link
        String resetLink = "http://localhost:8080/auth/reset-password?token=" + resetToken;
        emailService.sendEmail(user.getEmail(), "Reset Password",
                "Click the link to reset your password: " + resetLink);

        return ResponseEntity.ok("Password reset link sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid or expired token");
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Invalidate the token after use
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successfully");
    }



}
