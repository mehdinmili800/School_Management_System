package com.example.school_management_system.repository;

import com.example.school_management_system.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // البحث عن جميع سجلات الحضور لطالب معين
    List<Attendance> findByStudent_StudentId(Long studentId);

    // البحث عن سجل حضور لطالب معين في تاريخ معين
    List<Attendance> findByStudent_StudentIdAndDate(Long studentId, LocalDate date);
}
