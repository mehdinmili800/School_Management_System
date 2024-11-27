package com.example.school_management_system.dto.request;

import com.example.school_management_system.entity.Attendance;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttendanceDTO {

    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull(message = "Status is mandatory")
    private Attendance.Status status;
}
