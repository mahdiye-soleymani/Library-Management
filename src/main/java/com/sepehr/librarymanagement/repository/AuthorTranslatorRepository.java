package com.sepehr.librarymanagement.repository;

import com.sepehr.librarymanagement.entity.AuthorTranslator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorTranslatorRepository extends JpaRepository<AuthorTranslator, Long> {
}
