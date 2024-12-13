package com.example.school_management_system.service.service_Impl;


import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.dto.request.CreateStudentDTO;
import com.example.school_management_system.entity.Class;
import com.example.school_management_system.entity.Role;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.mapper.StudentMapper;
import com.example.school_management_system.repository.ClassRepository;
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
    private final ClassRepository classRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.classRepository = classRepository;
    }

    @Override
    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
        User user = userRepository.findById(createStudentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + createStudentDTO.getUserId()));

        if (!user.getRole().equals(Role.STUDENT)) {
            throw new RuntimeException("User role must be STUDENT to assign a class");
        }

        Class studentClass = classRepository.findById(createStudentDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + createStudentDTO.getClassId()));

        Student student = new Student();
        student.setUser(user);
        student.setAClass(studentClass);

        Student savedStudent = studentRepository.save(student);
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
