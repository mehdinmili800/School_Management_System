package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.ClassDTO;
import com.example.school_management_system.dto.request.CreateClassDTO;
import com.example.school_management_system.entity.Class;
import com.example.school_management_system.mapper.ClassMapper;
import com.example.school_management_system.repository.ClassRepository;
import com.example.school_management_system.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ClassDTO createClass(CreateClassDTO createClassDTO) {
        Class classEntity = new Class();
        classEntity.setClassName(createClassDTO.getClassName());
        classEntity.setGradeLevel(createClassDTO.getGradeLevel());

        Class savedClass = classRepository.save(classEntity);
        return ClassMapper.toDTO(savedClass);
    }

    @Override
    public ClassDTO getClassById(Long id) {
        Class classEntity = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));
        return ClassMapper.toDTO(classEntity);
    }

    @Override
    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(ClassMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClassDTO updateClass(Long id, ClassDTO classDTO) {
        Class existingClass = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));

        existingClass.setClassName(classDTO.getClassName());
        existingClass.setGradeLevel(classDTO.getGradeLevel());

        Class updatedClass = classRepository.save(existingClass);
        return ClassMapper.toDTO(updatedClass);
    }

    @Override
    public void deleteClass(Long id) {
        if (!classRepository.existsById(id)) {
            throw new RuntimeException("Class not found with ID: " + id);
        }
        classRepository.deleteById(id);
    }
}
