package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId; // Primary Key

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false) // Foreign Key to Classes
    private Class aClass;

    @Column(nullable = false)
    private String term; // Academic term

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false) // Foreign Key to Subjects
    private Subject subject;

    @Column(nullable = false)
    private LocalDate examDate; // Exam date
}
