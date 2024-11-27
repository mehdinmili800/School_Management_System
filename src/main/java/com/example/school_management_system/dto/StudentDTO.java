package com.example.school_management_system.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

    private Long studentId;      // Primary Key of Student
    private Long userId;         // Foreign Key reference to User
    private Long classId;        // Foreign Key to Class

}
