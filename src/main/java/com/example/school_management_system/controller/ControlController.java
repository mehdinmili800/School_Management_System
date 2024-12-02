package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ControlDTO;
import com.example.school_management_system.dto.request.CreateControlDTO;
import com.example.school_management_system.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controls")
public class ControlController {

    private final ControlService controlService;

    @Autowired
    public ControlController(ControlService controlService) {
        this.controlService = controlService;
    }

    @PostMapping("/createControl")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ControlDTO> createControl(@RequestBody CreateControlDTO createControlDTO) {
        ControlDTO control = controlService.createControl(createControlDTO);
        return ResponseEntity.ok(control);
    }

    @GetMapping("/getControlById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ControlDTO> getControlById(@PathVariable Long id) {
        ControlDTO control = controlService.getControlById(id);
        return ResponseEntity.ok(control);
    }

    @GetMapping("/getAllControls")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<ControlDTO>> getAllControls() {
        List<ControlDTO> controls = controlService.getAllControls();
        return ResponseEntity.ok(controls);
    }

    @DeleteMapping("/deleteControl/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteControl(@PathVariable Long id) {
        controlService.deleteControl(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/grades/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<ControlDTO>> getGradesByStudent(@PathVariable Long id) {
        List<ControlDTO> grades = controlService.getGradesByStudentId(id);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/exam/grades/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<ControlDTO>> getGradesByExam(@PathVariable Long id) {
        List<ControlDTO> grades = controlService.getGradesByExamId(id);
        return ResponseEntity.ok(grades);
    }

}
