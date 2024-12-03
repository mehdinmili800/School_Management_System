package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "Transport data transfer object representing a school bus and its associated students.")
public class TransportDTO {

    @Schema(description = "Unique identifier for the transport", example = "1")
    private Long transportId;

    @Schema(description = "Bus number of the transport", example = "BUS-101")
    private String busNumber;

    @Schema(description = "Name of the bus driver", example = "Ahmed Ali")
    private String driverName;

    @Schema(description = "Route name or number for the bus", example = "Route 5")
    private String route;

    @ArraySchema(schema = @Schema(description = "List of student IDs assigned to this bus", example = "[101, 102, 103]"))
    private List<Long> studentIds;
}
