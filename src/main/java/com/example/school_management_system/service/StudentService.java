package com.example.school_management_system.service;


import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.dto.request.CreateStudentDTO;

import java.util.List;

public interface StudentService {

    // Create a new student
    StudentDTO createStudent(CreateStudentDTO createStudentDTO);

    // Get a student by ID
    StudentDTO getStudentById(Long studentId);

    // Get all students
    List<StudentDTO> getAllStudents();



    // Update student details
    StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);

    // Delete a student by ID
    void deleteStudent(Long studentId);
}

