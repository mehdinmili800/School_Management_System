package com.example.school_management_system.mapper;


import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Student;

public class StudentMapper {

    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setUserId(student.getUser().getUserId());
        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        return student;
    }
}
