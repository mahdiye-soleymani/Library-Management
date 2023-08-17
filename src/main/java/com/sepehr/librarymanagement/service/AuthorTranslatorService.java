
package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.AuthorTranslator;

import java.util.List;

public interface AuthorTranslatorService {
    List<AuthorTranslator> getAllAuthorTranslators();

    AuthorTranslator getAuthorTranslatorById(Long id);

    AuthorTranslator createAuthorTranslator(AuthorTranslator authorTranslator);

    AuthorTranslator updateAuthorTranslator(Long id, AuthorTranslator authorTranslator);

    void deleteAuthorTranslator(Long id);
}