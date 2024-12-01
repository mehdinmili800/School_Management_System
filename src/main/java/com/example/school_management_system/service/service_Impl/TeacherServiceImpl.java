package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.TeacherDTO;
import com.example.school_management_system.dto.request.CreateTeacherDTO;
import com.example.school_management_system.entity.Role;
import com.example.school_management_system.entity.Subject;
import com.example.school_management_system.entity.Teacher;
import com.example.school_management_system.entity.User;
import com.example.school_management_system.mapper.TeacherMapper;
import com.example.school_management_system.repository.SubjectRepository;
import com.example.school_management_system.repository.TeacherRepository;
import com.example.school_management_system.repository.UserRepository;
import com.example.school_management_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO) {
        User user = userRepository.findById(createTeacherDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + createTeacherDTO.getUserId()));

        if (!user.getRole().equals(Role.TEACHER)) {
            throw new RuntimeException("User role must be TEACHER to assign a subject");
        }

        Subject subject = subjectRepository.findById(createTeacherDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + createTeacherDTO.getSubjectId()));

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setSubject(subject);

        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.toDTO(savedTeacher);
    }


    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));
        return TeacherMapper.toDTO(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));

        User user = userRepository.findById(teacherDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + teacherDTO.getUserId()));

        Subject subject = subjectRepository.findById(teacherDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + teacherDTO.getSubjectId()));

        existingTeacher.setUser(user);
        existingTeacher.setSubject(subject);

        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return TeacherMapper.toDTO(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with ID: " + id);
        }
        teacherRepository.deleteById(id);
    }
}
