package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId; // Primary Key

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // Foreign Key to User
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false) // Foreign Key to Subject
    private Subject subject;
}
