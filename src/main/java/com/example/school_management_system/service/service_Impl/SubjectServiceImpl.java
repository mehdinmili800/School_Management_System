package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.SubjectDTO;
import com.example.school_management_system.dto.request.CreateSubjectDTO;
import com.example.school_management_system.entity.Class;
import com.example.school_management_system.entity.Subject;
import com.example.school_management_system.mapper.SubjectMapper;
import com.example.school_management_system.repository.ClassRepository;
import com.example.school_management_system.repository.SubjectRepository;
import com.example.school_management_system.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, ClassRepository classRepository) {
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
    }

    @Override
    public SubjectDTO createSubject(CreateSubjectDTO createSubjectDTO) {
        // جلب الكلاس الصحيح
        Class studentClass = classRepository.findById(createSubjectDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + createSubjectDTO.getClassId()));

        // إنشاء كيان Subject
        Subject subject = new Subject();
        subject.setSubjectName(createSubjectDTO.getSubjectName());
        subject.setStudentClass(studentClass);

        // حفظ الكيان
        Subject savedSubject = subjectRepository.save(subject);

        // تحويل إلى DTO
        return SubjectMapper.toDTO(savedSubject);
    }

    @Override
    public SubjectDTO getSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));
        return SubjectMapper.toDTO(subject);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO updateSubject(Long subjectId, SubjectDTO subjectDTO) {
        Subject existingSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

        Class studentClass = classRepository.findById(subjectDTO.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + subjectDTO.getClassId()));

        existingSubject.setSubjectName(subjectDTO.getSubjectName());
        existingSubject.setStudentClass(studentClass);

        Subject updatedSubject = subjectRepository.save(existingSubject);
        return SubjectMapper.toDTO(updatedSubject);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new RuntimeException("Subject not found with ID: " + subjectId);
        }
        subjectRepository.deleteById(subjectId);
    }
}
