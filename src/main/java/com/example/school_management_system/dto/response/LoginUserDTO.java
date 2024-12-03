package com.example.school_management_system.dto.response;

import com.example.school_management_system.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for user login containing authentication details.")
public class LoginUserDTO {

    @Schema(description = "The JWT token generated for the authenticated user.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;   // The JWT token for authentication

    @Schema(description = "The email of the authenticated user.", example = "user@example.com")
    private String email; // The email of the authenticated user

    @Schema(description = "The role of the authenticated user.", example = "ADMIN", allowableValues = {"STUDENT", "TEACHER", "ADMIN"})
    private Role role;       // The role of the authenticated user (STUDENT, TEACHER, ADMIN)
}
