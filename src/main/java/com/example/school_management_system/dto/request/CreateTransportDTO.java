package com.example.school_management_system.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a new transport entry.")
public class CreateTransportDTO {

    @Schema(description = "Unique identifier for the bus.", example = "BUS-101", required = true)
    @NotBlank(message = "Bus Number is mandatory")
    private String busNumber;

    @Schema(description = "Full name of the driver assigned to the bus.", example = "John Doe", required = true)
    @NotBlank(message = "Driver Name is mandatory")
    private String driverName;

    @Schema(description = "The route or path the bus follows.", example = "Route A", required = true)
    @NotBlank(message = "Route is mandatory")
    private String route;
}
