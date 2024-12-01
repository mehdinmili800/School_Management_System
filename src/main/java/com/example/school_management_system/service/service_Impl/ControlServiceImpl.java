package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.ControlDTO;
import com.example.school_management_system.dto.request.CreateControlDTO;
import com.example.school_management_system.entity.Control;
import com.example.school_management_system.entity.Exam;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.mapper.ControlMapper;
import com.example.school_management_system.repository.ControlRepository;
import com.example.school_management_system.repository.ExamRepository;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ControlServiceImpl implements ControlService {

    private final ControlRepository controlRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    @Autowired
    public ControlServiceImpl(ControlRepository controlRepository, StudentRepository studentRepository, ExamRepository examRepository) {
        this.controlRepository = controlRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @Override
    public ControlDTO createControl(CreateControlDTO createControlDTO) {
        Student student = studentRepository.findById(createControlDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + createControlDTO.getStudentId()));

        Exam exam = examRepository.findById(createControlDTO.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + createControlDTO.getExamId()));

        Control control = new Control();
        control.setStudent(student);
        control.setExam(exam);
        control.setGrade(createControlDTO.getGrade());

        Control savedControl = controlRepository.save(control);
        return ControlMapper.toDTO(savedControl);
    }

    @Override
    public ControlDTO getControlById(Long id) {
        Control control = controlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Control not found with ID: " + id));
        return ControlMapper.toDTO(control);
    }

    @Override
    public List<ControlDTO> getAllControls() {
        return controlRepository.findAll()
                .stream()
                .map(ControlMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteControl(Long id) {
        if (!controlRepository.existsById(id)) {
            throw new RuntimeException("Control not found with ID: " + id);
        }
        controlRepository.deleteById(id);
    }
}
