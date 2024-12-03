package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for user login request.")
public class LoginRequestDTO {

    @Schema(description = "Valid email address of the user.", example = "user@example.com", required = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Schema(description = "Password of the user.", example = "securePassword123", required = true)
    @NotBlank(message = "Password is mandatory")
    private String password;
}
