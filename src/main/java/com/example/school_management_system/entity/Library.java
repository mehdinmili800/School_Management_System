package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    @Column(nullable = false, unique = true)
    private String title; // Title of the book

    @Column(nullable = false)
    private String author; // Author of the book

    @Column(nullable = false)
    private Integer totalCopies; // Total number of copies available

    @Column(nullable = false)
    private Integer borrowedCopies; // Number of borrowed copies

    @Column(nullable = false)
    private String status; // Status: AVAILABLE or UNAVAILABLE
}
