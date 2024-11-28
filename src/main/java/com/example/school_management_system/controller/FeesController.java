package com.example.school_management_system.controller;

import com.example.school_management_system.dto.FeesDTO;
import com.example.school_management_system.dto.PayFeesDTO;
import com.example.school_management_system.service.FeesService;
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

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeesDTO> createFee(@RequestBody FeesDTO feesDTO) {
        FeesDTO createdFee = feesService.createFee(feesDTO);
        return new ResponseEntity<>(createdFee, HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<List<FeesDTO>> getFeesByStudent(@PathVariable Long id) {
        List<FeesDTO> feesList = feesService.getFeesByStudent(id);
        return new ResponseEntity<>(feesList, HttpStatus.OK);
    }

    @PostMapping("/student/{id}/pay")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<FeesDTO> payFee(@PathVariable Long id, @RequestBody PayFeesDTO payFeesDTO) {
        FeesDTO updatedFee = feesService.payFee(id, payFeesDTO);
        return new ResponseEntity<>(updatedFee, HttpStatus.OK);
    }

    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FeesDTO>> getFeesReport() {
        List<FeesDTO> feesReport = feesService.getFeesReport();
        return new ResponseEntity<>(feesReport, HttpStatus.OK);
    }
}
