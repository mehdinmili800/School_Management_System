package com.example.school_management_system.controller;

import com.example.school_management_system.dto.FeesDTO;
import com.example.school_management_system.dto.PayFeesDTO;
import com.example.school_management_system.service.FeesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeesController {

    private final FeesService feesService;

    @Autowired
    public FeesController(FeesService feesService) {
        this.feesService = feesService;
    }

    @Operation(summary = "Create a fee record", description = "Allows an admin to create a new fee record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fee record created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeesDTO> createFee(@RequestBody FeesDTO feesDTO) {
        FeesDTO createdFee = feesService.createFee(feesDTO);
        return new ResponseEntity<>(createdFee, HttpStatus.CREATED);
    }

    @Operation(summary = "Get fees by student ID", description = "Allows a student or admin to retrieve the fee details of a specific student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fees retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<List<FeesDTO>> getFeesByStudent(@PathVariable Long id) {
        List<FeesDTO> feesList = feesService.getFeesByStudent(id);
        return new ResponseEntity<>(feesList, HttpStatus.OK);
    }

    @Operation(summary = "Pay a fee", description = "Allows a student or admin to pay a specific fee for a student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fee paid successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Fee or student not found")
    })
    @PostMapping("/student/{id}/pay")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<FeesDTO> payFee(@PathVariable Long id, @RequestBody PayFeesDTO payFeesDTO) {
        FeesDTO updatedFee = feesService.payFee(id, payFeesDTO);
        return new ResponseEntity<>(updatedFee, HttpStatus.OK);
    }

    @Operation(summary = "Get fees report", description = "Allows an admin to generate a complete report of all fees.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fees report retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FeesDTO>> getFeesReport() {
        List<FeesDTO> feesReport = feesService.getFeesReport();
        return new ResponseEntity<>(feesReport, HttpStatus.OK);
    }
}
