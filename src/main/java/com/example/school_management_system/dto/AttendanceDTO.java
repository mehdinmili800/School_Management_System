package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object representing attendance details for a student.")
public class AttendanceDTO {

    @Schema(description = "Unique identifier for the attendance record", example = "1")
    private Long attendanceId;

    @Schema(description = "Identifier of the student associated with this attendance record", example = "101")
    private Long studentId;

    @Schema(description = "Date of the attendance record", example = "2024-12-01")
    private LocalDate date;

    @Schema(description = "Attendance status of the student (e.g., 'Present', 'Absent', 'Late')", example = "Present")
    private String status; // You can later replace this with an enum if needed
}
