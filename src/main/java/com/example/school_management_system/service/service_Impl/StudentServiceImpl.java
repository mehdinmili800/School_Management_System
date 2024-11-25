package com.example.school_management_system.service.service_Impl;


import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.dto.request.CreateStudentDTO;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.mapper.StudentMapper;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
        // Fetch User
        User user = userRepository.findById(createStudentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + createStudentDTO.getUserId()));


        // Create and Save Student
        Student student = new Student();
        student.setUser(user);

        Student savedStudent = studentRepository.save(student);

        // Convert to DTO
        return StudentMapper.toDTO(savedStudent);
    }
    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        return StudentMapper.toDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }



    @Override
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));



        Student updatedStudent = studentRepository.save(existingStudent);
        return StudentMapper.toDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }
}
