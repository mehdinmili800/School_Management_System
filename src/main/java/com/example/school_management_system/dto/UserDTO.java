package com.example.school_management_system.dto;

import com.example.school_management_system.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDTO {

    public UserDTO(Long userId,  String email, Role role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
    }


    private Long userId; // For identification
    private String email;
    private Role role;


    // Exclude sensitive data like password
}
