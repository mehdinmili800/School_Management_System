package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a control record.")
public class CreateControlDTO {

    @Schema(description = "The ID of the student associated with the control record.", example = "101", required = true)
    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    @Schema(description = "The ID of the exam associated with the control record.", example = "202", required = true)
    @NotNull(message = "Exam ID is mandatory")
    private Long examId;

    @Schema(description = "The grade achieved by the student in the exam.", example = "85.5", required = true)
    @NotNull(message = "Grade is mandatory")
    private Float grade;
}
