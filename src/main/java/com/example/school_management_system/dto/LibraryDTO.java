package com.example.school_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Library data transfer object representing a book in the library.")
public class LibraryDTO {

    @Schema(description = "Unique identifier for the book", example = "1")
    private Long id;

    @Schema(description = "Title of the book", example = "To Kill a Mockingbird")
    private String title;

    @Schema(description = "Author of the book", example = "Harper Lee")
    private String author;

    @Schema(description = "Total number of copies available in the library", example = "50")
    private Integer totalCopies;

    @Schema(description = "Number of copies currently borrowed", example = "10")
    private Integer borrowedCopies;

    @Schema(description = "Current status of the book (e.g., Available, Out of Stock)", example = "Available")
    private String status;
}
