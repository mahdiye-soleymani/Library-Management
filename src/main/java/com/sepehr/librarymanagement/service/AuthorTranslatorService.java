package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.AuthorTranslator;

import java.util.List;

public interface AuthorTranslatorService {

    /**
     * Get a list of all author translators.
     *
     * @return List of author translators
     */
    List<AuthorTranslator> getAllAuthorTranslators();

    /**
     * Get an author translator by its ID.
     *
     * @param id ID of the author translator
     * @return Author translator with the specified ID
     */
    AuthorTranslator getAuthorTranslatorById(Long id);

    /**
     * Create a new author translator.
     *
     * @param authorTranslator The author translator to be created
     * @return Created author translator
     */
    AuthorTranslator createAuthorTranslator(AuthorTranslator authorTranslator);

    /**
     * Update an existing author translator.
     *
     * @param id               ID of the author translator to be updated
     * @param authorTranslator Updated author translator information
     * @return Updated author translator
     */
    AuthorTranslator updateAuthorTranslator(Long id, AuthorTranslator authorTranslator);

    /**
     * Delete an author translator by its ID.
     *
     * @param id ID of the author translator to be deleted
     */
    void deleteAuthorTranslator(Long id);
}
