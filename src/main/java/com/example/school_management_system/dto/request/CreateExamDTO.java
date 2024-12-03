package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a new exam.")
public class CreateExamDTO {

    @Schema(description = "The ID of the class associated with the exam.", example = "5", required = true)
    @NotNull(message = "Class ID is mandatory")
    private Long classId;

    @Schema(description = "The ID of the subject associated with the exam.", example = "7", required = true)
    @NotNull(message = "Subject ID is mandatory")
    private Long subjectId;

    @Schema(description = "The academic term for the exam.", example = "Spring 2024", required = true)
    @NotNull(message = "Term is mandatory")
    private String term;

    @Schema(description = "The date on which the exam is scheduled.", example = "2024-05-15", required = true)
    @NotNull(message = "Exam Date is mandatory")
    private LocalDate examDate;
}
