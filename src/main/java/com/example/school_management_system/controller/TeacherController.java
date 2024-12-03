package com.example.school_management_system.controller;

import com.example.school_management_system.dto.TeacherDTO;
import com.example.school_management_system.dto.request.CreateTeacherDTO;
import com.example.school_management_system.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Create Teacher", description = "Create a new teacher. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createTeacher")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody CreateTeacherDTO createTeacherDTO) {
        TeacherDTO teacher = teacherService.createTeacher(createTeacherDTO);
        return ResponseEntity.ok(teacher);
    }

    @Operation(summary = "Get Teacher by ID", description = "Retrieve teacher details by ID. Requires ADMIN or TEACHER role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher details retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @GetMapping("/getTeacherById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        TeacherDTO teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @Operation(summary = "Get All Teachers", description = "Retrieve a list of all teachers. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teachers retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getAllTeachers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @Operation(summary = "Update Teacher", description = "Update teacher details by ID. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher updated successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @PutMapping("/updateTeacher/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok(updatedTeacher);
    }

    @Operation(summary = "Delete Teacher", description = "Delete a teacher by ID. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @DeleteMapping("/deleteTeacher/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
