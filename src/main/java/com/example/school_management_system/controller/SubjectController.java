package com.example.school_management_system.controller;

import com.example.school_management_system.dto.SubjectDTO;
import com.example.school_management_system.dto.request.CreateSubjectDTO;
import com.example.school_management_system.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Operation(summary = "Create a new subject", description = "Allows an admin to create a new subject.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createSubject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody CreateSubjectDTO createSubjectDTO) {
        SubjectDTO subject = subjectService.createSubject(createSubjectDTO);
        return ResponseEntity.ok(subject);
    }

    @Operation(summary = "Get subject by ID", description = "Retrieve a subject's details by its ID. Accessible by ADMIN, TEACHER, or STUDENT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject details retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    @GetMapping("/getSubjectById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        SubjectDTO subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @Operation(summary = "Get all subjects", description = "Retrieve a list of all subjects. Accessible by ADMIN, TEACHER, or STUDENT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subjects retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getAllSubjects")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @Operation(summary = "Update subject", description = "Allows an admin to update subject details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject updated successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    @PutMapping("/updateSubject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectDTO subjectDTO) {
        SubjectDTO updatedSubject = subjectService.updateSubject(id, subjectDTO);
        return ResponseEntity.ok(updatedSubject);
    }

    @Operation(summary = "Delete subject", description = "Allows an admin to delete a subject by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subject deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    @DeleteMapping("/deleteSubject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
