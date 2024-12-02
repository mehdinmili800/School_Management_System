package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.TransportDTO;
import com.example.school_management_system.entity.Transport;

import java.util.stream.Collectors;

public class TransportMapper {

    public static TransportDTO toDTO(Transport transport) {
        TransportDTO dto = new TransportDTO();
        dto.setTransportId(transport.getTransportId());
        dto.setBusNumber(transport.getBusNumber());
        dto.setDriverName(transport.getDriverName());
        dto.setRoute(transport.getRoute());
        dto.setStudentIds(transport.getStudents() != null
                ? transport.getStudents().stream().map(student -> student.getStudentId()).collect(Collectors.toList())
                : null);
        return dto;
    }
}

