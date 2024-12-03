package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object representing an exam.")
public class ExamDTO {

    @Schema(description = "Unique identifier for the exam", example = "1")
    private Long examId;

    @Schema(description = "Identifier for the class associated with the exam", example = "101")
    private Long classId;

    @Schema(description = "Identifier for the subject associated with the exam", example = "202")
    private Long subjectId;

    @Schema(description = "Academic term during which the exam is conducted", example = "Fall 2024")
    private String term;

    @Schema(description = "Date of the exam", example = "2024-12-15")
    private LocalDate examDate;
}
