package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Pay fees data transfer object for handling fee payments.")
public class PayFeesDTO {

    @Schema(description = "Unique identifier for the fee being paid", example = "101")
    private Long feeId; // ID of the fee

    @Schema(description = "The amount being paid by the user", example = "1500.50")
    private Double amountPaid; // Amount paid
}
