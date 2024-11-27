package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId; // Primary Key

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key to Users
    private User user;

    @ManyToOne
    @JoinColumn(name = "class_id",nullable = false) // foreign Key to Classes
    private Class aClass;



}


