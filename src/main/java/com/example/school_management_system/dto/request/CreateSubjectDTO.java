package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a new subject.")
public class CreateSubjectDTO {

    @Schema(description = "The name of the subject.", example = "Mathematics", required = true)
    @NotBlank(message = "Subject name is mandatory")
    private String subjectName;

    @Schema(description = "The ID of the class associated with the subject.", example = "5", required = true)
    @NotNull(message = "Class ID is mandatory")
    private Long classId;
}
