package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ExamDTO;
import com.example.school_management_system.dto.request.CreateExamDTO;
import com.example.school_management_system.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/createExam")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamDTO> createExam(@RequestBody CreateExamDTO createExamDTO) {
        ExamDTO exam = examService.createExam(createExamDTO);
        return ResponseEntity.ok(exam);
    }

    @GetMapping("/getExamById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        ExamDTO exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @GetMapping("/getAllExams")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @PutMapping("/updateExam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamDTO> updateExam(@PathVariable Long id, @RequestBody ExamDTO examDTO) {
        ExamDTO updatedExam = examService.updateExam(id, examDTO);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/deleteExam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}

