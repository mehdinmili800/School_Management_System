package com.example.school_management_system.controller;

import com.example.school_management_system.dto.TransportDTO;
import com.example.school_management_system.dto.request.CreateTransportDTO;
import com.example.school_management_system.service.TransportService;
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

    @PostMapping("/createTransport")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransportDTO> createTransport(@RequestBody CreateTransportDTO createTransportDTO) {
        TransportDTO transport = transportService.createTransport(createTransportDTO);
        return ResponseEntity.ok(transport);
    }

    @PostMapping("/assignStudentToBus/{id}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignStudentToBus(@PathVariable Long id, @RequestParam Long studentId) {
        transportService.assignStudentToBus(id, studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getTransportById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<TransportDTO> getTransportById(@PathVariable Long id) {
        TransportDTO transport = transportService.getTransportById(id);
        return ResponseEntity.ok(transport);
    }

    @GetMapping("/getTransportReport")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransportDTO>> getTransportReport() {
        List<TransportDTO> report = transportService.getTransportReport();
        return ResponseEntity.ok(report);
    }

    @PutMapping("/updateTransport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransportDTO> updateTransport(@PathVariable Long id, @RequestBody CreateTransportDTO updateTransportDTO) {
        TransportDTO updatedTransport = transportService.updateTransport(id, updateTransportDTO);
        return ResponseEntity.ok(updatedTransport);
    }

    @DeleteMapping("/deleteTransport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long id) {
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removeStudentFromBus/{transportId}/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeStudentFromSpecificBus(@PathVariable Long transportId, @PathVariable Long studentId) {
        transportService.removeStudentFromSpecificBus(transportId, studentId);
        return ResponseEntity.noContent().build();
    }

}

