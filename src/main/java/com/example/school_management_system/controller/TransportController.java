package com.example.school_management_system.controller;

import com.example.school_management_system.dto.TransportDTO;
import com.example.school_management_system.dto.request.CreateTransportDTO;
import com.example.school_management_system.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transport")
public class TransportController {

    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @Operation(summary = "Create Transport", description = "Create a new transport entry. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/createTransport")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransportDTO> createTransport(@RequestBody CreateTransportDTO createTransportDTO) {
        TransportDTO transport = transportService.createTransport(createTransportDTO);
        return ResponseEntity.ok(transport);
    }

    @Operation(summary = "Assign Student to Bus", description = "Assign a student to a specific bus. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student assigned to bus successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Transport or student not found")
    })
    @PostMapping("/assignStudentToBus/{id}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignStudentToBus(@PathVariable Long id, @RequestParam Long studentId) {
        transportService.assignStudentToBus(id, studentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Transport by ID", description = "Retrieve transport details by ID. Requires ADMIN or TEACHER role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport details retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Transport not found")
    })
    @GetMapping("/getTransportById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<TransportDTO> getTransportById(@PathVariable Long id) {
        TransportDTO transport = transportService.getTransportById(id);
        return ResponseEntity.ok(transport);
    }

    @Operation(summary = "Get Transport Report", description = "Retrieve a detailed transport report. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport report retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/getTransportReport")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransportDTO>> getTransportReport() {
        List<TransportDTO> report = transportService.getTransportReport();
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Update Transport", description = "Update transport details by ID. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport updated successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Transport not found")
    })
    @PutMapping("/updateTransport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransportDTO> updateTransport(@PathVariable Long id, @RequestBody CreateTransportDTO updateTransportDTO) {
        TransportDTO updatedTransport = transportService.updateTransport(id, updateTransportDTO);
        return ResponseEntity.ok(updatedTransport);
    }

    @Operation(summary = "Delete Transport", description = "Delete a transport entry by ID. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transport deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Transport not found")
    })
    @DeleteMapping("/deleteTransport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long id) {
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove Student from Bus", description = "Remove a specific student from a specific bus. Requires ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student removed from bus successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Transport or student not found")
    })
    @DeleteMapping("/removeStudentFromBus/{transportId}/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeStudentFromSpecificBus(@PathVariable Long transportId, @PathVariable Long studentId) {
        transportService.removeStudentFromSpecificBus(transportId, studentId);
        return ResponseEntity.noContent().build();
    }

}
