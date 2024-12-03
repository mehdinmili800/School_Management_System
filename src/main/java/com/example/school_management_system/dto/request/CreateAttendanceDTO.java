package com.example.school_management_system.dto.request;

import com.example.school_management_system.entity.Attendance;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for creating attendance records.")
public class CreateAttendanceDTO {

    @Schema(description = "The ID of the student for whom the attendance is being recorded.", example = "123", required = true)
    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    @Schema(description = "The date of the attendance record.", example = "2024-12-01", required = true)
    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Schema(description = "The attendance status of the student.", example = "PRESENT", required = true, allowableValues = {"PRESENT", "ABSENT"})
    @NotNull(message = "Status is mandatory")
    private Attendance.Status status;
}
