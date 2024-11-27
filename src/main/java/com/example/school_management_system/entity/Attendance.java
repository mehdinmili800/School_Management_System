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
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId; // المفتاح الأساسي

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false) // مفتاح أجنبي للإشارة إلى الطالب
    private Student student;

    private LocalDate date; // تاريخ الحضور

    @Enumerated(EnumType.STRING)
    private Status status; // حالة الحضور (حاضر، غائب، متأخر)

    public enum Status {
        PRESENT, ABSENT, LATE
    }


}
