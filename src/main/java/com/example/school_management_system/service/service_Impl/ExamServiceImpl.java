package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.ExamDTO;
import com.example.school_management_system.dto.request.CreateExamDTO;
import com.example.school_management_system.entity.Class;
import com.example.school_management_system.entity.Exam;
import com.example.school_management_system.entity.Subject;
import com.example.school_management_system.mapper.ExamMapper;
import com.example.school_management_system.repository.ClassRepository;
import com.example.school_management_system.repository.ExamRepository;
import com.example.school_management_system.repository.SubjectRepository;
import com.example.school_management_system.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository, ClassRepository classRepository, SubjectRepository subjectRepository) {
        this.examRepository = examRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public ExamDTO createExam(CreateExamDTO createExamDTO) {
        Class aClass = classRepository.findById(createExamDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Subject subject = subjectRepository.findById(createExamDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Exam exam = new Exam();
        exam.setAClass(aClass);
        exam.setSubject(subject);
        exam.setTerm(createExamDTO.getTerm());
        exam.setExamDate(createExamDTO.getExamDate());

        Exam savedExam = examRepository.save(exam);
        return ExamMapper.toDTO(savedExam);
    }

    @Override
    public ExamDTO getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        return ExamMapper.toDTO(exam);
    }

    @Override
    public List<ExamDTO> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(ExamMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDTO updateExam(Long id, ExamDTO examDTO) {
        Exam existingExam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Class aClass = classRepository.findById(examDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Subject subject = subjectRepository.findById(examDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        existingExam.setAClass(aClass);
        existingExam.setSubject(subject);
        existingExam.setTerm(examDTO.getTerm());
        existingExam.setExamDate(examDTO.getExamDate());

        Exam updatedExam = examRepository.save(existingExam);
        return ExamMapper.toDTO(updatedExam);
    }

    @Override
    public void deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new RuntimeException("Exam not found");
        }
        examRepository.deleteById(id);
    }
}
