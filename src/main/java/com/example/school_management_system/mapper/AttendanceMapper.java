package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.AttendanceDTO;
import com.example.school_management_system.dto.request.CreateAttendanceDTO;
import com.example.school_management_system.entity.Attendance;
import com.example.school_management_system.entity.Student;

public class AttendanceMapper {

    public static AttendanceDTO toDTO(Attendance attendance) {
        return new AttendanceDTO(
                attendance.getAttendanceId(),
                attendance.getStudent().getStudentId(),
                attendance.getDate(),
                attendance.getStatus().name()
        );
    }

    public static Attendance toEntity(CreateAttendanceDTO dto, Student student) {
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());
        return attendance;
    }
}

