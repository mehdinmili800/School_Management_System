package com.example.school_management_system.repository;

import com.example.school_management_system.entity.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {
    List<Control> findByStudent_StudentId(Long studentId);
    List<Control> findByExam_ExamId(Long examId);
}
