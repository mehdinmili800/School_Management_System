package com.example.school_management_system.dto.request;

import com.example.school_management_system.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateUserDTO {

    @Schema(description = "User's email address. Must be unique and valid.", example = "user@example.com")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Schema(description = "Password for the user account.", example = "SecurePass123!")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Schema(description = "Username for the user.", example = "john_doe")
    @NotBlank(message = "User Name is mandatory")
    private String username;

    @Schema(description = "Phone number of the user.", example = "+1234567890")
    @NotBlank(message = "Phone Number")
    private String phone_number;

    @Schema(description = "Role of the user. Can be STUDENT, TEACHER, or ADMIN.", example = "STUDENT")
    private Role role;
}
