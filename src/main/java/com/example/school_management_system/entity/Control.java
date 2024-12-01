package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long controlId; // Primary Key

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false) // Foreign Key to Students
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false) // Foreign Key to Exams
    private Exam exam;

    @Column(nullable = false)
    private Float grade; // Final grade
}
