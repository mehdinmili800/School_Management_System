package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId; // Primary Key

    @Column(nullable = false)
    private String subjectName; // Name of the subject

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false) // Foreign Key to Class
    private Class studentClass; // Reference to Class entity
}
