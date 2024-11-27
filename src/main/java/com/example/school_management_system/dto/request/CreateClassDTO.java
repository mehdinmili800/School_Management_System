package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateClassDTO {

    @NotBlank(message = "Class name is mandatory")
    private String className;

    @NotNull(message = "Grade level is mandatory")
    private Integer gradeLevel;
}
