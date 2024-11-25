package com.example.school_management_system.controller;


import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.dto.request.CreateStudentDTO;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final UserRepository userRepository;


    @Autowired
    public StudentController(StudentService studentService, UserRepository userRepository) {
        this.studentService = studentService;
        this.userRepository = userRepository;
    }

    // يسمح فقط للمشرفين (ADMIN) بإضافة طالب
    @PostMapping("/createStudent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody CreateStudentDTO createStudentDTO) {
        StudentDTO studentDTO = studentService.createStudent(createStudentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    // يسمح فقط للطلاب (STUDENT) بالوصول إلى بياناتهم
    @GetMapping("/getStudent/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // مثال آخر لدالة يمكن أن تكون مخصصة للطلاب أو المشرفين معاً
    @GetMapping("/getAllStudents")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // 3. تعديل بيانات طالب - متاحة فقط للمشرفين (ADMIN)
    @PutMapping("/updateStudent/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    // البحث المتقدم عن الطلاب
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam(required = false) String email) {
        // جلب كل الطلاب
        List<StudentDTO> students = studentService.getAllStudents();

        // تصفية النتائج بناءً على البريد الإلكتروني (إذا تم توفيره)
        if (email != null && !email.isEmpty()) {
            students = students.stream()
                    .filter(student -> student.getUserId() != null && studentService.getStudentById(student.getStudentId())
                            .getUserId().equals(userRepository.findByEmail(email).map(User::getUserId).orElse(null)))
                    .toList();
        }

        return ResponseEntity.ok(students);
    }

}
