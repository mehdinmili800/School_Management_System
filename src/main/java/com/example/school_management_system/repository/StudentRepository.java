package com.example.school_management_system.repository;

import com.example.school_management_system.entity.Student;
import com.example.school_management_system.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // البحث عن الطلاب المرتبطين بحافلة معينة
    List<Student> findByTransport(Transport transport);
}
