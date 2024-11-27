package com.example.school_management_system.service;

import com.example.school_management_system.dto.ClassDTO;
import com.example.school_management_system.dto.request.CreateClassDTO;

import java.util.List;

public interface ClassService {
    ClassDTO createClass(CreateClassDTO createClassDTO);

    ClassDTO getClassById(Long id);

    List<ClassDTO> getAllClasses();

    ClassDTO updateClass(Long id, ClassDTO classDTO);

    void deleteClass(Long id);
}
