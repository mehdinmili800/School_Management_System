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
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false) // Foreign Key to Student
    private Student student;

    @Column(nullable = false)
    private Double amount; // Total fee amount

    @Column(nullable = false)
    private Double amountPaid; // Amount paid

    @Column(nullable = false)
    private LocalDate dueDate; // Due date for payment

    private String notes; // Optional notes about the fee

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status; // Payment status (PAID, PARTIALLY_PAID, UNPAID)

    public enum PaymentStatus {
        PAID, PARTIALLY_PAID, UNPAID
    }
}
