package com.example.school_management_system.service;


import com.example.school_management_system.dto.LibraryDTO;

import java.util.List;

public interface LibraryService {

    LibraryDTO addBook(LibraryDTO libraryDTO); // Add a new book

    LibraryDTO getBookById(Long id); // Get book details by ID

    LibraryDTO borrowBook(Long id); // Borrow a book

    LibraryDTO returnBook(Long id); // Return a borrowed book

    List<LibraryDTO> getLibraryReport(); // Get report of borrowed and available books
}
