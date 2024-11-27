package com.example.school_management_system.dto;

import com.example.school_management_system.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDTO {

    public UserDTO(Long userId,String username,  String email, String phone_number, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }


    private Long userId; // For identification
    private String username;
    private String email;
    private String phone_number;
    private Role role;


    // Exclude sensitive data like password
}
