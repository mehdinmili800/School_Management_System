package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object representing a class in the school system.")
public class ClassDTO {

    @Schema(description = "Unique identifier for the class", example = "1")
    private Long classId;

    @Schema(description = "Name of the class", example = "Grade 10 - A")
    private String className;

    @Schema(description = "Grade level of the class", example = "10")
    private Integer gradeLevel;
}
