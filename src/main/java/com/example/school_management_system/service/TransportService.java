package com.example.school_management_system.service;

import com.example.school_management_system.dto.TransportDTO;
import com.example.school_management_system.dto.request.CreateTransportDTO;

import java.util.List;

public interface TransportService {
    TransportDTO createTransport(CreateTransportDTO createTransportDTO);
    void assignStudentToBus(Long transportId, Long studentId);
    TransportDTO getTransportById(Long id);
    List<TransportDTO> getTransportReport();
    TransportDTO updateTransport(Long id, CreateTransportDTO updateTransportDTO);
    void deleteTransport(Long id);
    void removeStudentFromSpecificBus(Long transportId, Long studentId);

}

