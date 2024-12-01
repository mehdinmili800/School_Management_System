package com.example.school_management_system.dto.request;

import com.example.school_management_system.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateUserDTO {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "User Name is mandatory")
    private String username;

    @NotBlank(message = "Phone Number")
    private String phone_number;

    private Role role;
}
