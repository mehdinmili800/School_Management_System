package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ClassDTO;
import com.example.school_management_system.dto.request.CreateClassDTO;
import com.example.school_management_system.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @Operation(summary = "Create a new class", description = "This endpoint allows an admin to create a new class.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Class created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createClass")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassDTO> createClass(@RequestBody CreateClassDTO createClassDTO) {
        ClassDTO classDTO = classService.createClass(createClassDTO);
        return new ResponseEntity<>(classDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get class by ID", description = "Retrieve a specific class by its ID. Accessible by admins and teachers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Class not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getClassById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        ClassDTO classDTO = classService.getClassById(id);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get all classes", description = "Retrieve a list of all classes. Accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of classes retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getAllClasses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        List<ClassDTO> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @Operation(summary = "Update a class", description = "Allows an admin to update an existing class by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class updated successfully"),
            @ApiResponse(responseCode = "404", description = "Class not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PutMapping("/updateClass/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable Long id, @RequestBody ClassDTO classDTO) {
        ClassDTO updatedClass = classService.updateClass(id, classDTO);
        return new ResponseEntity<>(updatedClass, HttpStatus.OK);
    }

    @Operation(summary = "Delete a class", description = "Allows an admin to delete a specific class by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Class not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @DeleteMapping("/deleteClass/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return new ResponseEntity<>("Class deleted successfully", HttpStatus.OK);
    }
}
