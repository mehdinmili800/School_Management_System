package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.ExamDTO;
import com.example.school_management_system.entity.Class;
import com.example.school_management_system.entity.Exam;
import com.example.school_management_system.entity.Subject;

public class ExamMapper {

    public static ExamDTO toDTO(Exam exam) {
        ExamDTO dto = new ExamDTO();
        dto.setExamId(exam.getExamId());
        dto.setClassId(exam.getAClass().getClassId());
        dto.setSubjectId(exam.getSubject().getSubjectId());
        dto.setTerm(exam.getTerm());
        dto.setExamDate(exam.getExamDate());
        return dto;
    }

    public static Exam toEntity(ExamDTO dto, Class aClass, Subject subject) {
        Exam exam = new Exam();
        exam.setExamId(dto.getExamId());
        exam.setAClass(aClass);
        exam.setSubject(subject);
        exam.setTerm(dto.getTerm());
        exam.setExamDate(dto.getExamDate());
        return exam;
    }
}
