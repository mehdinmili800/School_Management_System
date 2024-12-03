package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Student data transfer object representing a student and their associated details.")
public class StudentDTO {

    @Schema(description = "Unique identifier for the student", example = "1")
    private Long studentId;      // Primary Key of Student

    @Schema(description = "Unique identifier for the user associated with the student", example = "5")
    private Long userId;         // Foreign Key reference to User

    @Schema(description = "Unique identifier for the class associated with the student", example = "10")
    private Long classId;        // Foreign Key to Class
}
