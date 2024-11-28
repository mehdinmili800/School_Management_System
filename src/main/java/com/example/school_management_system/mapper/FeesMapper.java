package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.FeesDTO;
import com.example.school_management_system.entity.Fees;
import com.example.school_management_system.entity.Student;

public class FeesMapper {

    public static FeesDTO toDTO(Fees fees) {
        return new FeesDTO(
                fees.getId(),
                fees.getStudent().getStudentId(),
                fees.getAmount(),
                fees.getAmountPaid(),
                fees.getDueDate(),
                fees.getNotes(),
                fees.getStatus().name()
        );
    }

    public static Fees toEntity(FeesDTO dto, Student student) {
        Fees fees = new Fees();
        fees.setStudent(student);
        fees.setAmount(dto.getAmount());
        fees.setAmountPaid(dto.getAmountPaid());
        fees.setDueDate(dto.getDueDate());
        fees.setNotes(dto.getNotes());
        fees.setStatus(Fees.PaymentStatus.valueOf(dto.getStatus()));
        return fees;
    }
}
