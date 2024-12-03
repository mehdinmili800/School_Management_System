package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object representing student fees.")
public class FeesDTO {

    @Schema(description = "Unique identifier for the fee record", example = "1")
    private Long id;

    @Schema(description = "Identifier for the student associated with the fee", example = "101")
    private Long studentId;

    @Schema(description = "Total amount of the fee", example = "500.00")
    private Double amount;

    @Schema(description = "Amount that has already been paid", example = "200.00")
    private Double amountPaid;

    @Schema(description = "Due date for the fee payment", example = "2024-12-31")
    private LocalDate dueDate;

    @Schema(description = "Additional notes related to the fee", example = "Partial payment received")
    private String notes;

    @Schema(description = "Current status of the fee (e.g., Pending, Paid, Overdue)", example = "Pending")
    private String status;
}
