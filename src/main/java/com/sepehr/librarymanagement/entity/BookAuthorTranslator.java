package com.sepehr.librarymanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book_author_translators")
@Data
public class BookAuthorTranslator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_translator_id")
    private AuthorTranslator authorTranslator;

}