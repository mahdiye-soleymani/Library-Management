
package com.sepehr.librarymanagement.controller;

import com.sepehr.librarymanagement.dto.AuthorTranslatorDTO;
import com.sepehr.librarymanagement.entity.AuthorTranslator;
import com.sepehr.librarymanagement.service.AuthorTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorTranslators")
public class AuthorTranslatorController {
    private final AuthorTranslatorService authorTranslatorService;

    @Autowired
    public AuthorTranslatorController(AuthorTranslatorService authorTranslatorService) {
        this.authorTranslatorService = authorTranslatorService;
    }

    @GetMapping
    public List<AuthorTranslator> getAllAuthorTranslators() {
        return authorTranslatorService.getAllAuthorTranslators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorTranslator> getAuthorTranslatorById(@PathVariable Long id) {
        AuthorTranslator authorTranslator = authorTranslatorService.getAuthorTranslatorById(id);
        if (authorTranslator != null) {
            return ResponseEntity.ok(authorTranslator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AuthorTranslator> createAuthorTranslator(@RequestBody AuthorTranslatorDTO authorTranslatorDTO) {
        AuthorTranslator authorTranslator = new AuthorTranslator();
        // Set properties from DTO to the authorTranslator object
        authorTranslator.setName(authorTranslatorDTO.getName());
        authorTranslator.setType(authorTranslatorDTO.getType());

        AuthorTranslator createdAuthorTranslator = authorTranslatorService.createAuthorTranslator(authorTranslator);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthorTranslator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorTranslator> updateAuthorTranslator(@PathVariable Long id, @RequestBody AuthorTranslatorDTO authorTranslatorDTO) {
        AuthorTranslator existingAuthorTranslator = authorTranslatorService.getAuthorTranslatorById(id);
        if (existingAuthorTranslator != null) {
            // Update properties from DTO to the existingAuthorTranslator object
            existingAuthorTranslator.setName(authorTranslatorDTO.getName());
            existingAuthorTranslator.setType(authorTranslatorDTO.getType());

            AuthorTranslator updatedAuthorTranslator = authorTranslatorService.updateAuthorTranslator(id, existingAuthorTranslator);
            if (updatedAuthorTranslator != null) {
                return ResponseEntity.ok(updatedAuthorTranslator);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorTranslator(@PathVariable Long id) {
        authorTranslatorService.deleteAuthorTranslator(id);
        return ResponseEntity.noContent().build();
    }
}