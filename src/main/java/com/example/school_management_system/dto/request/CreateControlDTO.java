package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateControlDTO {

    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    @NotNull(message = "Exam ID is mandatory")
    private Long examId;

    @NotNull(message = "Grade is mandatory")
    private Float grade;
}
