package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a new student.")
public class CreateStudentDTO {

    @Schema(description = "The ID of the user associated with the student.", example = "123", required = true)
    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @Schema(description = "The ID of the class the student belongs to.", example = "10", required = true)
    @NotNull(message = "Class ID is mandatory")
    private Long classId;
}
