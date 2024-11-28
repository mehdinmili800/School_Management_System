package com.example.school_management_system.controller;


import com.example.school_management_system.dto.LibraryDTO;
import com.example.school_management_system.service.LibraryService;
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

    @PostMapping("/books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LibraryDTO> addBook(@RequestBody LibraryDTO libraryDTO) {
        LibraryDTO createdBook = libraryService.addBook(libraryDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/books/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LibraryDTO> getBookById(@PathVariable Long id) {
        LibraryDTO book = libraryService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/books/{id}/borrow")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
    public ResponseEntity<LibraryDTO> borrowBook(@PathVariable Long id) {
        LibraryDTO borrowedBook = libraryService.borrowBook(id);
        return new ResponseEntity<>(borrowedBook, HttpStatus.OK);
    }

    @PostMapping("/books/{id}/return")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER')")
    public ResponseEntity<LibraryDTO> returnBook(@PathVariable Long id) {
        LibraryDTO returnedBook = libraryService.returnBook(id);
        return new ResponseEntity<>(returnedBook, HttpStatus.OK);
    }

    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LibraryDTO>> getLibraryReport() {
        List<LibraryDTO> report = libraryService.getLibraryReport();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}

