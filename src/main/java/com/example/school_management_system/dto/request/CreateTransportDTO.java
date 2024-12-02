package com.example.school_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTransportDTO {

    @NotBlank(message = "Bus Number is mandatory")
    private String busNumber;

    @NotBlank(message = "Driver Name is mandatory")
    private String driverName;

    @NotBlank(message = "Route is mandatory")
    private String route;
}

