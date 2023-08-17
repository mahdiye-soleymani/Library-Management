package com.sepehr.librarymanagement.repository;

import com.sepehr.librarymanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    Book findByAuthorsFirstNameContainingOrAuthorsLastNameContaining(String firstName, String lastName);

    Book findByTranslatorsFirstNameContainingOrTranslatorsLastNameContaining(String firstName, String lastName);

}