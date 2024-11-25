package com.example.school_management_system.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateStudentDTO {
    private Long userId;    // Foreign Key to Users
}
