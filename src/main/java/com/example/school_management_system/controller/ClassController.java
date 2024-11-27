package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ClassDTO;
import com.example.school_management_system.dto.request.CreateClassDTO;
import com.example.school_management_system.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("/createClass")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassDTO> createClass(@RequestBody CreateClassDTO createClassDTO) {
        ClassDTO classDTO = classService.createClass(createClassDTO);
        return new ResponseEntity<>(classDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getClassById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        ClassDTO classDTO = classService.getClassById(id);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllClasses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        List<ClassDTO> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PutMapping("/updateClass/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable Long id, @RequestBody ClassDTO classDTO) {
        ClassDTO updatedClass = classService.updateClass(id, classDTO);
        return new ResponseEntity<>(updatedClass, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClass/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return new ResponseEntity<>("Class deleted successfully", HttpStatus.OK);
    }
}
