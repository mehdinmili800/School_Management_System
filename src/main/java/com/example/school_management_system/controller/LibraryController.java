package com.example.school_management_system.controller;

import com.example.school_management_system.dto.LibraryDTO;
import com.example.school_management_system.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Operation(summary = "Add a new book", description = "Allows an admin to add a new book to the library.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book added successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @PostMapping("/books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LibraryDTO> addBook(@RequestBody LibraryDTO libraryDTO) {
        LibraryDTO createdBook = libraryService.addBook(libraryDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @Operation(summary = "Get book by ID", description = "Allows students, teachers, and admins to retrieve details of a specific book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/books/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LibraryDTO> getBookById(@PathVariable Long id) {
        LibraryDTO book = libraryService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(summary = "Borrow a book", description = "Allows students and teachers to borrow a book.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book borrowed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Book not found or not available for borrowing")
    })
    @PostMapping("/books/{id}/borrow")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
    public ResponseEntity<LibraryDTO> borrowBook(@PathVariable Long id) {
        LibraryDTO borrowedBook = libraryService.borrowBook(id);
        return new ResponseEntity<>(borrowedBook, HttpStatus.OK);
    }

    @Operation(summary = "Return a book", description = "Allows students and teachers to return a borrowed book.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book returned successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Book not found or not borrowed by the user")
    })
    @PostMapping("/books/{id}/return")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
    public ResponseEntity<LibraryDTO> returnBook(@PathVariable Long id) {
        LibraryDTO returnedBook = libraryService.returnBook(id);
        return new ResponseEntity<>(returnedBook, HttpStatus.OK);
    }

    @Operation(summary = "Get library report", description = "Allows an admin to retrieve a report of all books in the library.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library report retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient permissions")
    })
    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LibraryDTO>> getLibraryReport() {
        List<LibraryDTO> report = libraryService.getLibraryReport();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
