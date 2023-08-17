package com.sepehr.librarymanagement.entity;

import com.sepehr.librarymanagement.enums.AuthorTranslatorType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authors_translators")
@Data
public class AuthorTranslator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AuthorTranslatorType type;

}
