package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.LibraryDTO;
import com.example.school_management_system.entity.Library;

public class LibraryMapper {

    public static LibraryDTO toDTO(Library library) {
        return new LibraryDTO(
                library.getId(),
                library.getTitle(),
                library.getAuthor(),
                library.getTotalCopies(),
                library.getBorrowedCopies(),
                library.getStatus()
        );
    }

    public static Library toEntity(LibraryDTO dto) {
        Library library = new Library();
        library.setId(dto.getId());
        library.setTitle(dto.getTitle());
        library.setAuthor(dto.getAuthor());
        library.setTotalCopies(dto.getTotalCopies());
        library.setBorrowedCopies(dto.getBorrowedCopies());
        library.setStatus(dto.getStatus());
        return library;
    }
}

