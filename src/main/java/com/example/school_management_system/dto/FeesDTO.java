package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesDTO {

    private Long id;
    private Long studentId;
    private Double amount;
    private Double amountPaid;
    private LocalDate dueDate;
    private String notes;
    private String status;
}
