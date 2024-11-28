package com.example.school_management_system.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayFeesDTO {

    private Long feeId;
    private Double amountPaid;
}
