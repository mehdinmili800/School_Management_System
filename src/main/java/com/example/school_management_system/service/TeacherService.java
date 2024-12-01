package com.example.school_management_system.service;

import com.example.school_management_system.dto.TeacherDTO;
import com.example.school_management_system.dto.request.CreateTeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO);
    TeacherDTO getTeacherById(Long id);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);
    void deleteTeacher(Long id);
}
