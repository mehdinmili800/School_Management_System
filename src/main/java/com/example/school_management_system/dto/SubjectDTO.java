package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Subject data transfer object representing a subject and its associated details.")
public class SubjectDTO {

    @Schema(description = "Unique identifier for the subject", example = "101")
    private Long subjectId;       // Primary Key

    @Schema(description = "Name of the subject", example = "Mathematics")
    private String subjectName;   // Name of the subject

    @Schema(description = "Class ID associated with the subject", example = "5")
    private Long classId;         // Foreign Key to Class
}
