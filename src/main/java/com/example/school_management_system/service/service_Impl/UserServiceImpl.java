package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.UserDTO;
import com.example.school_management_system.dto.request.CreateUserDTO;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.mapper.UserMapper;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole(createUserDTO.getRole());

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUserId(), savedUser.getEmail(), savedUser.getRole());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return new UserDTO(user.getUserId(), user.getEmail(), user.getRole());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRole(userDTO.getRole());
        // Do not update the password unless explicitly required

        User updatedUser = userRepository.save(existingUser);
        return new UserDTO(updatedUser.getUserId(), updatedUser.getEmail(), updatedUser.getRole());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
