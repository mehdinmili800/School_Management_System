package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportId; // Primary Key

    @Column(nullable = false, unique = true)
    private String busNumber; // Unique Bus Number

    @Column(nullable = false)
    private String driverName; // Driver Name

    @Column(nullable = false)
    private String route; // Route Description

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students; // List of students assigned to this bus
}
