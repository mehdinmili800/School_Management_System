package com.example.school_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TransportDTO {
    private Long transportId;
    private String busNumber;
    private String driverName;
    private String route;
    private List<Long> studentIds; // List of student IDs assigned to this bus
}
