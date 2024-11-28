package com.example.school_management_system.service;


import com.example.school_management_system.dto.FeesDTO;
import com.example.school_management_system.dto.PayFeesDTO;

import java.util.List;

public interface FeesService {

    FeesDTO createFee(FeesDTO feesDTO); // Add a new fee

    List<FeesDTO> getFeesByStudent(Long studentId); // Get fees for a specific student

    FeesDTO payFee(Long studentId, PayFeesDTO payFeesDTO); // Record a fee payment

    List<FeesDTO> getFeesReport(); // Get detailed fees report
}

