package com.example.school_management_system.repository;


import com.example.school_management_system.entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Long> {

    List<Fees> findByStudent_StudentId(Long studentId); // Fetch fees by student ID
}
