package com.example.school_management_system.mapper;


import com.example.school_management_system.dto.UserDTO;
import com.example.school_management_system.entity.User;

public class UserMapper {

    // Convert from Entity to DTO
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone_number(user.getPhone_number());
        dto.setRole(user.getRole()); // Correctly map the Role
        return dto;
    }

    // Convert from DTO to Entity
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone_number(dto.getPhone_number());
        user.setRole(dto.getRole()); // Correctly map the Role
        return user;
    }
}
