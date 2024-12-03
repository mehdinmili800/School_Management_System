package com.example.school_management_system.controller;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.dto.request.CreateStudentDTO;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final UserRepository userRepository;

    @Autowired
    public StudentController(StudentService studentService, UserRepository userRepository) {
        this.studentService = studentService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Create a new student", description = "Allows an admin to create a new student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createStudent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody CreateStudentDTO createStudentDTO) {
        StudentDTO studentDTO = studentService.createStudent(createStudentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get student by ID", description = "Retrieve student details by their ID. Accessible by ADMIN and the student themselves.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student details retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/getStudent/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get all students", description = "Retrieve a list of all students. Accessible by ADMIN and students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getAllStudents")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Update student details", description = "Allows an admin to update a student's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/updateStudent/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @Operation(summary = "Search students", description = "Allows an admin to search for students by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam(required = false) String email) {
        List<StudentDTO> students = studentService.getAllStudents();

        if (email != null && !email.isEmpty()) {
            students = students.stream()
                    .filter(student -> student.getUserId() != null && studentService.getStudentById(student.getStudentId())
                            .getUserId().equals(userRepository.findByEmail(email).map(User::getUserId).orElse(null)))
                    .toList();
        }

        return ResponseEntity.ok(students);
    }
}
