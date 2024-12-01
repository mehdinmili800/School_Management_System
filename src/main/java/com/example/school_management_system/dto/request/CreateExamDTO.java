package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateExamDTO {

    @NotNull(message = "Class ID is mandatory")
    private Long classId;

    @NotNull(message = "Subject ID is mandatory")
    private Long subjectId;

    @NotNull(message = "Term is mandatory")
    private String term;

    @NotNull(message = "Exam Date is mandatory")
    private LocalDate examDate;
}

