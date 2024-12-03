package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Teacher data transfer object representing a teacher and their associated details.")
public class TeacherDTO {

    @Schema(description = "Unique identifier for the teacher", example = "1")
    private Long teacherId;      // Primary Key

    @Schema(description = "User ID associated with the teacher", example = "10")
    private Long userId;         // Foreign Key to User

    @Schema(description = "Subject ID taught by the teacher", example = "5")
    private Long subjectId;      // Foreign Key to Subject
}
