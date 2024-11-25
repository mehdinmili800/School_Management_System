package com.example.school_management_system.service_test;


import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.dto.request.CreateStudentDTO;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.mapper.StudentMapper;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.service_Impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        CreateStudentDTO createStudentDTO = new CreateStudentDTO();
        createStudentDTO.setUserId(1L);

        User user = new User();
        user.setUserId(1L);
        user.setEmail("test@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Student student = new Student();
        student.setStudentId(1L);
        student.setUser(user);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO result = studentService.createStudent(createStudentDTO);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetStudentById() {
        User user = new User(1L, "test@example.com", "password", null, null, null);
        Student student = new Student(1L, user);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentDTO result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllStudents() {
        User user1 = new User(1L, "test1@example.com", "password1", null, null, null);
        User user2 = new User(2L, "test2@example.com", "password2", null, null, null);

        Student student1 = new Student(1L, user1);
        Student student2 = new Student(2L, user2);

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        List<StudentDTO> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getStudentId());
        assertEquals(2L, result.get(1).getStudentId());
        verify(studentRepository, times(1)).findAll();
    }
}
