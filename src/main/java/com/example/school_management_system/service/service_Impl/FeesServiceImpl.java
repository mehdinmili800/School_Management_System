package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.FeesDTO;
import com.example.school_management_system.dto.PayFeesDTO;
import com.example.school_management_system.entity.Fees;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.mapper.FeesMapper;
import com.example.school_management_system.repository.FeesRepository;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public FeesServiceImpl(FeesRepository feesRepository, StudentRepository studentRepository) {
        this.feesRepository = feesRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FeesDTO createFee(FeesDTO feesDTO) {
        Student student = studentRepository.findById(feesDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + feesDTO.getStudentId()));

        Fees fee = FeesMapper.toEntity(feesDTO, student);
        Fees savedFee = feesRepository.save(fee);

        return FeesMapper.toDTO(savedFee);
    }

    @Override
    public List<FeesDTO> getFeesByStudent(Long studentId) {
        return feesRepository.findByStudent_StudentId(studentId)
                .stream()
                .map(FeesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeesDTO payFee(Long studentId, PayFeesDTO payFeesDTO) {
        Fees fee = feesRepository.findById(payFeesDTO.getFeeId())
                .orElseThrow(() -> new RuntimeException("Fee not found with ID: " + payFeesDTO.getFeeId()));

        if (!fee.getStudent().getStudentId().equals(studentId)) {
            throw new RuntimeException("Fee does not belong to the specified student");
        }

        double newAmountPaid = fee.getAmountPaid() + payFeesDTO.getAmountPaid();
        fee.setAmountPaid(newAmountPaid);
        if (newAmountPaid >= fee.getAmount()) {
            fee.setStatus(Fees.PaymentStatus.PAID);
        } else {
            fee.setStatus(Fees.PaymentStatus.PARTIALLY_PAID);
        }

        Fees updatedFee = feesRepository.save(fee);
        return FeesMapper.toDTO(updatedFee);
    }

    @Override
    public List<FeesDTO> getFeesReport() {
        return feesRepository.findAll()
                .stream()
                .map(FeesMapper::toDTO)
                .collect(Collectors.toList());
    }
}
