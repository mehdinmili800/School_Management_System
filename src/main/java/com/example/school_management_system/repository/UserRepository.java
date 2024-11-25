package com.example.school_management_system.repository;


import com.example.school_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    // البحث عن مستخدم باستخدام البريد الإلكتروني
    Optional<User> findByEmail(String email);

    // البحث عن مستخدم باستخدام رمز إعادة التعيين
    Optional<User> findByResetToken(String resetToken);

    // Check if a username already exists
    boolean existsByEmail(String email);



}
