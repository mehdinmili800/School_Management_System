package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTeacherDTO {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Subject ID is mandatory")
    private Long subjectId;
}
