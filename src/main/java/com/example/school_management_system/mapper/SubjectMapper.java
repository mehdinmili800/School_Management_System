package com.example.school_management_system.mapper;


import com.example.school_management_system.dto.SubjectDTO;
import com.example.school_management_system.entity.Class;
import com.example.school_management_system.entity.Subject;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setSubjectId(subject.getSubjectId());
        dto.setSubjectName(subject.getSubjectName());
        dto.setClassId(subject.getStudentClass().getClassId());
        return dto;
    }

    public static Subject toEntity(SubjectDTO dto, Class studentClass) {
        Subject subject = new Subject();
        subject.setSubjectId(dto.getSubjectId());
        subject.setSubjectName(dto.getSubjectName());
        subject.setStudentClass(studentClass);
        return subject;
    }
}

