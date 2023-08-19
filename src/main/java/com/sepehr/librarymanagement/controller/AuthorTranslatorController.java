
package com.sepehr.librarymanagement.controller;

import com.sepehr.librarymanagement.dto.AuthorTranslatorDTO;
import com.sepehr.librarymanagement.entity.AuthorTranslator;
import com.sepehr.librarymanagement.service.AuthorTranslatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Get all author translators")
    @GetMapping
    public List<AuthorTranslator> getAllAuthorTranslators() {
        return authorTranslatorService.getAllAuthorTranslators();
    }

    @ApiOperation("Get an author translator by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Author translator found"),
            @ApiResponse(code = 404, message = "Author translator not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorTranslator> getAuthorTranslatorById(@PathVariable Long id) {
        AuthorTranslator authorTranslator = authorTranslatorService.getAuthorTranslatorById(id);
        if (authorTranslator != null) {
            return ResponseEntity.ok(authorTranslator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Create a new author translator")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Author translator created successfully")
    })
    @PostMapping
    public ResponseEntity<AuthorTranslator> createAuthorTranslator(@RequestBody AuthorTranslatorDTO authorTranslatorDTO) {
        AuthorTranslator authorTranslator = new AuthorTranslator();
        authorTranslator.setName(authorTranslatorDTO.getName());
        authorTranslator.setType(authorTranslatorDTO.getType());

        AuthorTranslator createdAuthorTranslator = authorTranslatorService.createAuthorTranslator(authorTranslator);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthorTranslator);
    }

    @ApiOperation("Update an author translator by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Author translator updated successfully"),
            @ApiResponse(code = 404, message = "Author translator not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuthorTranslator> updateAuthorTranslator(@PathVariable Long id, @RequestBody AuthorTranslatorDTO authorTranslatorDTO) {
        AuthorTranslator existingAuthorTranslator = authorTranslatorService.getAuthorTranslatorById(id);
        if (existingAuthorTranslator != null) {
            existingAuthorTranslator.setName(authorTranslatorDTO.getName());
            existingAuthorTranslator.setType(authorTranslatorDTO.getType());

            AuthorTranslator updatedAuthorTranslator = authorTranslatorService.updateAuthorTranslator(id, existingAuthorTranslator);
            if (updatedAuthorTranslator != null) {
                return ResponseEntity.ok(updatedAuthorTranslator);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation("Delete an author translator by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Author translator deleted successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorTranslator(@PathVariable Long id) {
        authorTranslatorService.deleteAuthorTranslator(id);
        return ResponseEntity.noContent().build();
    }

}