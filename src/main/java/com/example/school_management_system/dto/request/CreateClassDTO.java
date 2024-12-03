package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a class.")
public class CreateClassDTO {

    @Schema(description = "The name of the class.", example = "Mathematics", required = true)
    @NotBlank(message = "Class name is mandatory")
    private String className;

    @Schema(description = "The grade level of the class.", example = "10", required = true)
    @NotNull(message = "Grade level is mandatory")
    private Integer gradeLevel;
}
