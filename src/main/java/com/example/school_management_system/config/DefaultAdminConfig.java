package com.example.school_management_system.config;

import com.example.school_management_system.entity.User;
import com.example.school_management_system.entity.Role;
import com.example.school_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultAdminConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createDefaultAdmin() {
        return args -> {
            // بيانات المسؤول الافتراضي
            String adminEmail = "mehdi800wa@gmail.com"; // يمكنك تغييره إذا أردت
            String adminPassword = "mehdi"; // اجعلها كلمة مرور قوية

            // تحقق إذا كان الحساب موجودًا بالفعل
            if (!userRepository.existsByEmail(adminEmail)) {
                // إنشاء المسؤول
                User admin = new User();
                admin.setUsername("Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setPhone_number("0000000000"); // رقم افتراضي
                admin.setRole(Role.ADMIN);

                // حفظ المسؤول في قاعدة البيانات
                userRepository.save(admin);
                System.out.println("Default admin account created successfully!");
            } else {
                System.out.println("Default admin account already exists.");
            }
        };
    }
}
