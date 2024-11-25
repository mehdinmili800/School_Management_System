package com.example.school_management_system.service;

import com.example.school_management_system.dto.UserDTO;
import com.example.school_management_system.dto.request.CreateUserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(CreateUserDTO createUserDTO); // Create a new user

    UserDTO getUserById(Long id); // Get user details by ID

    List<UserDTO> getAllUsers(); // Retrieve all users

    UserDTO updateUser(Long id, UserDTO userDTO); // Update user details

    void deleteUser(Long id); // Delete user by ID
}
