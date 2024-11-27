package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.AttendanceDTO;
import com.example.school_management_system.dto.request.CreateAttendanceDTO;
import com.example.school_management_system.entity.Attendance;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.mapper.AttendanceMapper;
import com.example.school_management_system.repository.AttendanceRepository;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public AttendanceDTO createAttendance(CreateAttendanceDTO dto) {
        // تحقق من وجود الطالب
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));

        // تحويل DTO إلى كيان
        Attendance attendance = AttendanceMapper.toEntity(dto, student);

        // حفظ الكيان
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // تحويل الكيان المحفوظ إلى DTO
        return AttendanceMapper.toDTO(savedAttendance);
    }

    @Override
    public List<AttendanceDTO> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudent_StudentId(studentId)
                .stream()
                .map(AttendanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByStudentAndDate(Long studentId, LocalDate date) {
        return attendanceRepository.findByStudent_StudentIdAndDate(studentId, date)
                .stream()
                .map(AttendanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAttendance(Long attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new RuntimeException("Attendance not found with ID: " + attendanceId);
        }
        attendanceRepository.deleteById(attendanceId);
    }
}

