package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.TeacherDTO;
import com.example.school_management_system.entity.Subject;
import com.example.school_management_system.entity.Teacher;
import com.example.school_management_system.entity.User;

public class TeacherMapper {

    public static TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setUserId(teacher.getUser().getUserId());
        dto.setSubjectId(teacher.getSubject().getSubjectId());
        return dto;
    }

    public static Teacher toEntity(TeacherDTO dto, User user, Subject subject) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(dto.getTeacherId());
        teacher.setUser(user);
        teacher.setSubject(subject);
        return teacher;
    }
}
