package com.example.school_management_system.dto.response;

import com.example.school_management_system.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDTO {

    private String token;   // The JWT token for authentication
    private String email; // The username of the authenticated user
    private Role role;       // The role of the authenticated user (STUDENT, TEACHER, ADMIN)
}
