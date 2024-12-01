package com.example.school_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExamDTO {
    private Long examId;
    private Long classId;
    private Long subjectId;
    private String term;
    private LocalDate examDate;
}
