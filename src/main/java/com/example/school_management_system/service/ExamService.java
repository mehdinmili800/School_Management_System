package com.example.school_management_system.service;

import com.example.school_management_system.dto.ExamDTO;
import com.example.school_management_system.dto.request.CreateExamDTO;

import java.util.List;

public interface ExamService {
    ExamDTO createExam(CreateExamDTO createExamDTO);
    ExamDTO getExamById(Long id);
    List<ExamDTO> getAllExams();
    ExamDTO updateExam(Long id, ExamDTO examDTO);
    void deleteExam(Long id);
}
