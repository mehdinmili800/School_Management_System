package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.ClassDTO;
import com.example.school_management_system.entity.Class;

public class ClassMapper {

    public static ClassDTO toDTO(Class classEntity) {
        ClassDTO dto = new ClassDTO();
        dto.setClassId(classEntity.getClassId());
        dto.setClassName(classEntity.getClassName());
        dto.setGradeLevel(classEntity.getGradeLevel());
        return dto;
    }

    public static Class toEntity(ClassDTO dto) {
        Class classEntity = new Class();
        classEntity.setClassId(dto.getClassId());
        classEntity.setClassName(dto.getClassName());
        classEntity.setGradeLevel(dto.getGradeLevel());
        return classEntity;
    }
}
