package com.example.school_management_system.service;

import com.example.school_management_system.dto.AttendanceDTO;
import com.example.school_management_system.dto.request.CreateAttendanceDTO;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceDTO createAttendance(CreateAttendanceDTO dto);

    List<AttendanceDTO> getAttendanceByStudent(Long studentId);

    List<AttendanceDTO> getAttendanceByStudentAndDate(Long studentId, LocalDate date);

    void deleteAttendance(Long attendanceId);
}
