package com.example.school_management_system.service;

import com.example.school_management_system.dto.SubjectDTO;
import com.example.school_management_system.dto.request.CreateSubjectDTO;

import java.util.List;

public interface SubjectService {
    SubjectDTO createSubject(CreateSubjectDTO createSubjectDTO);
    SubjectDTO getSubjectById(Long id);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO);
    void deleteSubject(Long id);
}
