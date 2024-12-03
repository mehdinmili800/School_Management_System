package com.example.school_management_system.dto;

import com.example.school_management_system.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "User data transfer object representing user details.")
public class UserDTO {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long userId;

    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Phone number of the user", example = "+123456789")
    private String phone_number;

    @Schema(description = "Role of the user", example = "ADMIN")
    private Role role;

    public UserDTO(Long userId, String username, String email, String phone_number, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }
}
