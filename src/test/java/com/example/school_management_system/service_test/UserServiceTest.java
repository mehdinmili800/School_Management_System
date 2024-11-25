package com.example.school_management_system.service_test;


import com.example.school_management_system.dto.UserDTO;
import com.example.school_management_system.dto.request.CreateUserDTO;
import com.example.school_management_system.entity.Role;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.service_Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("test@example.com");
        createUserDTO.setPassword("password123");
        createUserDTO.setRole(Role.STUDENT);

        when(userRepository.existsByEmail(createUserDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(createUserDTO.getPassword())).thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setUserId(1L);
        savedUser.setEmail(createUserDTO.getEmail());
        savedUser.setPassword("encodedPassword");
        savedUser.setRole(Role.STUDENT);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDTO result = userService.createUser(createUserDTO);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals(Role.STUDENT, result.getRole());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(1L);
        user.setEmail("test@example.com");
        user.setRole(Role.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals(Role.ADMIN, result.getRole());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1L, "test1@example.com", "password1", Role.STUDENT, null, null);
        User user2 = new User(2L, "test2@example.com", "password2", Role.TEACHER, null, null);

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("test1@example.com", result.get(0).getEmail());
        assertEquals(Role.TEACHER, result.get(1).getRole());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User(1L, "old@example.com", "password", Role.STUDENT, null, null);
        UserDTO updateDTO = new UserDTO(1L, "new@example.com", Role.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        UserDTO result = userService.updateUser(1L, updateDTO);

        assertNotNull(result);
        assertEquals("new@example.com", result.getEmail());
        assertEquals(Role.ADMIN, result.getRole());

        verify(userRepository, times(1)).save(any(User.class));
    }
}
