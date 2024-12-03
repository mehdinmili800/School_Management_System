package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object representing control records for student grades.")
public class ControlDTO {

    @Schema(description = "Unique identifier for the control record", example = "1")
    private Long controlId;

    @Schema(description = "Identifier of the student associated with this record", example = "101")
    private Long studentId;

    @Schema(description = "Identifier of the exam associated with this record", example = "202")
    private Long examId;

    @Schema(description = "Grade achieved by the student in the exam", example = "85.5")
    private Float grade;
}
