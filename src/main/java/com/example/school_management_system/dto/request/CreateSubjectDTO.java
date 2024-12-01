package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateSubjectDTO {

    @NotBlank(message = "Subject name is mandatory")
    private String subjectName; // Name of the subject

    @NotNull(message = "Class ID is mandatory")
    private Long classId; // Foreign Key to Class
}
