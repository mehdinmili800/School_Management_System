package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ControlDTO;
import com.example.school_management_system.dto.request.CreateControlDTO;
import com.example.school_management_system.service.ControlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controls")
public class ControlController {

    private final ControlService controlService;

    @Autowired
    public ControlController(ControlService controlService) {
        this.controlService = controlService;
    }

    @Operation(summary = "Create a control record", description = "Allows an admin to create a new control record for a student's grade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Control record created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createControl")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ControlDTO> createControl(@RequestBody CreateControlDTO createControlDTO) {
        ControlDTO control = controlService.createControl(createControlDTO);
        return ResponseEntity.ok(control);
    }

    @Operation(summary = "Get control record by ID", description = "Retrieve a specific control record by its ID. Accessible by admins and teachers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Control record retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Control record not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getControlById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ControlDTO> getControlById(@PathVariable Long id) {
        ControlDTO control = controlService.getControlById(id);
        return ResponseEntity.ok(control);
    }

    @Operation(summary = "Get all control records", description = "Retrieve a list of all control records. Accessible by admins and teachers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of control records retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getAllControls")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<ControlDTO>> getAllControls() {
        List<ControlDTO> controls = controlService.getAllControls();
        return ResponseEntity.ok(controls);
    }

    @Operation(summary = "Delete a control record", description = "Allows an admin to delete a control record by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Control record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Control record not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @DeleteMapping("/deleteControl/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteControl(@PathVariable Long id) {
        controlService.deleteControl(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get grades by student ID", description = "Retrieve all grades associated with a specific student. Accessible by admins, teachers, and students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of grades retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/student/grades/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<ControlDTO>> getGradesByStudent(@PathVariable Long id) {
        List<ControlDTO> grades = controlService.getGradesByStudentId(id);
        return ResponseEntity.ok(grades);
    }

    @Operation(summary = "Get grades by exam ID", description = "Retrieve all grades associated with a specific exam. Accessible by admins and teachers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of grades retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Exam not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/exam/grades/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<ControlDTO>> getGradesByExam(@PathVariable Long id) {
        List<ControlDTO> grades = controlService.getGradesByExamId(id);
        return ResponseEntity.ok(grades);
    }
}
