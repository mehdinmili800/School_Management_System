package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateStudentDTO {

    @NotNull(message = "User ID is mandatory")
    private Long userId;    // Foreign Key to Users

    @NotNull(message = "Class ID is mandatory")
    private Long classId;   // Foreign Key to Classes
}
