
package com.sepehr.librarymanagement.service.impl;

import com.sepehr.librarymanagement.entity.AuthorTranslator;
import com.sepehr.librarymanagement.repository.AuthorTranslatorRepository;
import com.sepehr.librarymanagement.service.AuthorTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorTranslatorServiceImpl implements AuthorTranslatorService {
    private final AuthorTranslatorRepository authorTranslatorRepository;

    @Autowired
    public AuthorTranslatorServiceImpl(AuthorTranslatorRepository authorTranslatorRepository) {
        this.authorTranslatorRepository = authorTranslatorRepository;
    }

    @Override
    public List<AuthorTranslator> getAllAuthorTranslators() {
        return authorTranslatorRepository.findAll();
    }

    @Override
    public AuthorTranslator getAuthorTranslatorById(Long id) {
        return authorTranslatorRepository.findById(id).orElse(null);
    }

    @Override
    public AuthorTranslator createAuthorTranslator(AuthorTranslator authorTranslator) {
        return authorTranslatorRepository.save(authorTranslator);
    }

    @Override
    public AuthorTranslator updateAuthorTranslator(Long id, AuthorTranslator authorTranslator) {
        if (authorTranslatorRepository.existsById(id)) {
            authorTranslator.setId(id);
            return authorTranslatorRepository.save(authorTranslator);
        }
        return null;
    }

    @Override
    public void deleteAuthorTranslator(Long id) {
        authorTranslatorRepository.deleteById(id);
    }
}
