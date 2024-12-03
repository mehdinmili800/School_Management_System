package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ExamDTO;
import com.example.school_management_system.dto.request.CreateExamDTO;
import com.example.school_management_system.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create an exam", description = "Allows an admin to create a new exam.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createExam")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamDTO> createExam(@RequestBody CreateExamDTO createExamDTO) {
        ExamDTO exam = examService.createExam(createExamDTO);
        return ResponseEntity.ok(exam);
    }

    @Operation(summary = "Get exam by ID", description = "Retrieve details of a specific exam by its ID. Accessible to admins, teachers, and students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Exam not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getExamById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        ExamDTO exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @Operation(summary = "Get all exams", description = "Retrieve a list of all exams. Accessible to admins, teachers, and students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getAllExams")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @Operation(summary = "Update an exam", description = "Allows an admin to update an existing exam by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam updated successfully"),
            @ApiResponse(responseCode = "404", description = "Exam not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PutMapping("/updateExam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamDTO> updateExam(@PathVariable Long id, @RequestBody ExamDTO examDTO) {
        ExamDTO updatedExam = examService.updateExam(id, examDTO);
        return ResponseEntity.ok(updatedExam);
    }

    @Operation(summary = "Delete an exam", description = "Allows an admin to delete an exam by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exam deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Exam not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @DeleteMapping("/deleteExam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
