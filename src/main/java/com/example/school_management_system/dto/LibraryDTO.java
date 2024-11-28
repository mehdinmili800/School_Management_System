package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {

    private Long id;
    private String title;
    private String author;
    private Integer totalCopies;
    private Integer borrowedCopies;
    private String status;
}
