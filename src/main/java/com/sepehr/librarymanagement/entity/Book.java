
package com.sepehr.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "book_number")
    private String bookNumber;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "publishers")
    private String publishers;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "edition_number")
    private int editionNumber;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookAuthorTranslator> bookAuthorTranslators;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Member borrower;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowedBook> borrowedBooks;

}