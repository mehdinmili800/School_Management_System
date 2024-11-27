package com.example.school_management_system.controller;

import com.example.school_management_system.dto.AttendanceDTO;
import com.example.school_management_system.dto.request.CreateAttendanceDTO;
import com.example.school_management_system.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody @Valid CreateAttendanceDTO createAttendanceDTO) {
        AttendanceDTO attendanceDTO = attendanceService.createAttendance(createAttendanceDTO);
        return new ResponseEntity<>(attendanceDTO, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByStudent(@PathVariable Long studentId) {
        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByStudent(studentId);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/date")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByStudentAndDate(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByStudentAndDate(studentId, date);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{attendanceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
