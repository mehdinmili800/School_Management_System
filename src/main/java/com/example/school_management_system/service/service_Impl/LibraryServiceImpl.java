package com.example.school_management_system.service.service_Impl;


import com.example.school_management_system.dto.LibraryDTO;
import com.example.school_management_system.entity.Library;
import com.example.school_management_system.entity.LibraryMapper;
import com.example.school_management_system.repository.LibraryRepository;
import com.example.school_management_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public LibraryDTO addBook(LibraryDTO libraryDTO) {
        Library library = LibraryMapper.toEntity(libraryDTO);
        library.setBorrowedCopies(0);
        library.setStatus("AVAILABLE");

        Library savedLibrary = libraryRepository.save(library);
        return LibraryMapper.toDTO(savedLibrary);
    }

    @Override
    public LibraryDTO getBookById(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        return LibraryMapper.toDTO(library);
    }

    @Override
    public LibraryDTO borrowBook(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        if (library.getTotalCopies() > library.getBorrowedCopies()) {
            library.setBorrowedCopies(library.getBorrowedCopies() + 1);
            if (library.getTotalCopies().equals(library.getBorrowedCopies())) {
                library.setStatus("UNAVAILABLE");
            }
            libraryRepository.save(library);
            return LibraryMapper.toDTO(library);
        } else {
            throw new RuntimeException("No available copies for book ID: " + id);
        }
    }

    @Override
    public LibraryDTO returnBook(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        if (library.getBorrowedCopies() > 0) {
            library.setBorrowedCopies(library.getBorrowedCopies() - 1);
            library.setStatus("AVAILABLE");
            libraryRepository.save(library);
            return LibraryMapper.toDTO(library);
        } else {
            throw new RuntimeException("No borrowed copies to return for book ID: " + id);
        }
    }

    @Override
    public List<LibraryDTO> getLibraryReport() {
        return libraryRepository.findAll()
                .stream()
                .map(LibraryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
