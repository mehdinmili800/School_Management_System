package com.example.school_management_system.service.service_Impl;

import com.example.school_management_system.dto.TransportDTO;
import com.example.school_management_system.dto.request.CreateTransportDTO;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.entity.Transport;
import com.example.school_management_system.mapper.TransportMapper;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.repository.TransportRepository;
import com.example.school_management_system.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository, StudentRepository studentRepository) {
        this.transportRepository = transportRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public TransportDTO createTransport(CreateTransportDTO createTransportDTO) {
        Transport transport = new Transport();
        transport.setBusNumber(createTransportDTO.getBusNumber());
        transport.setDriverName(createTransportDTO.getDriverName());
        transport.setRoute(createTransportDTO.getRoute());

        Transport savedTransport = transportRepository.save(transport);
        return TransportMapper.toDTO(savedTransport);
    }

    @Override
    public void assignStudentToBus(Long transportId, Long studentId) {
        Transport transport = transportRepository.findById(transportId)
                .orElseThrow(() -> new RuntimeException("Transport not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setTransport(transport);
        studentRepository.save(student);
    }

    @Override
    public TransportDTO getTransportById(Long id) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport not found"));
        return TransportMapper.toDTO(transport);
    }

    @Override
    public List<TransportDTO> getTransportReport() {
        return transportRepository.findAll()
                .stream()
                .map(TransportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransportDTO updateTransport(Long id, CreateTransportDTO updateTransportDTO) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport not found with ID: " + id));

        transport.setBusNumber(updateTransportDTO.getBusNumber());
        transport.setDriverName(updateTransportDTO.getDriverName());
        transport.setRoute(updateTransportDTO.getRoute());

        Transport updatedTransport = transportRepository.save(transport);
        return TransportMapper.toDTO(updatedTransport);
    }

    @Override
    public void deleteTransport(Long transportId) {
        Transport transport = transportRepository.findById(transportId)
                .orElseThrow(() -> new RuntimeException("Transport not found with ID: " + transportId));

        // حذف جميع الطلاب المرتبطين بالحافلة
        List<Student> students = studentRepository.findByTransport(transport);
        students.forEach(student -> {
            student.setTransport(null);
            studentRepository.save(student);
        });

        // الآن يمكن حذف الحافلة
        transportRepository.delete(transport);
    }


    @Override
    public void removeStudentFromSpecificBus(Long transportId, Long studentId) {
        // Fetch the transport by ID
        Transport transport = transportRepository.findById(transportId)
                .orElseThrow(() -> new RuntimeException("Transport not found with ID: " + transportId));

        // Fetch the student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Check if the student is assigned to the given transport
        if (student.getTransport() == null || !student.getTransport().getTransportId().equals(transportId)) {
            throw new RuntimeException("Student is not assigned to the specified transport.");
        }

        // Remove the transport association from the student
        student.setTransport(null);

        // Save the updated student
        studentRepository.save(student);
    }


}
