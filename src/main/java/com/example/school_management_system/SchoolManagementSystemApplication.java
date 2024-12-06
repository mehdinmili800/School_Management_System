package com.example.school_management_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "School Management System API",
                version = "1.0",
                description = "Admin : mehdi@gmail.com  n/ ///////// Password : mehdi"
        )
)
public class SchoolManagementSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(SchoolManagementSystemApplication.class, args);
    }

}
