package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a new teacher.")
public class CreateTeacherDTO {

    @Schema(description = "Unique identifier for the user (teacher).", example = "101", required = true)
    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @Schema(description = "Unique identifier for the subject the teacher will teach.", example = "202", required = true)
    @NotNull(message = "Subject ID is mandatory")
    private Long subjectId;
}
