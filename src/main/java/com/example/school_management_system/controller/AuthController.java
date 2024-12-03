package com.example.school_management_system.controller;

import com.example.school_management_system.config.JwtUtils;
import com.example.school_management_system.dto.request.CreateUserDTO;
import com.example.school_management_system.dto.request.LoginRequestDTO;
import com.example.school_management_system.dto.response.LoginUserDTO;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.UserService;
import com.example.school_management_system.service.email.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration. These endpoints are open and do not require authentication.")
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



    @Operation(summary = "Register a new user", description = "This endpoint allows registering a new user with required information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setPhone_number(createUserDTO.getPhone_number());
        user.setRole(createUserDTO.getRole());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(summary = "Login user", description = "Authenticate user with email and password, and return a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginUserDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = jwtUtils.generateToken(user.getEmail(), List.of(user.getRole().name()));

        return ResponseEntity.ok(new LoginUserDTO(token, user.getEmail(), user.getRole()));
    }

    @Operation(summary = "Refresh token", description = "Generate a new JWT token based on an existing valid token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid or expired token")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginUserDTO> refreshToken(@RequestHeader("Authorization") String oldToken) {
        String token = oldToken.replace("Bearer ", "");
        if (!jwtUtils.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String email = jwtUtils.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newToken = jwtUtils.generateToken(user.getEmail(), List.of(user.getRole().name()));

        return ResponseEntity.ok(new LoginUserDTO(newToken, user.getEmail(), user.getRole()));
    }

    @Operation(summary = "Forgot password", description = "Send a password reset link to the user's email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset link sent"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        userRepository.save(user);

        String resetLink = "http://localhost:8080/auth/reset-password?token=" + resetToken;
        emailService.sendEmail(user.getEmail(), "Reset Password",
                "Click the link to reset your password: " + resetLink);

        return ResponseEntity.ok("Password reset link sent to your email");
    }

    @Operation(summary = "Reset password", description = "Reset user's password using a valid reset token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid or expired token")
    })
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid or expired token");
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successfully");
    }
}
