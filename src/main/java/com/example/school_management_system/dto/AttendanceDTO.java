package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

    private Long attendanceId;
    private Long studentId;
    private LocalDate date;
    private String status; // Can use String for simplicity in enums
}
