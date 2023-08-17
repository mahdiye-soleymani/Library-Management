package com.sepehr.librarymanagement.controller;

import com.sepehr.librarymanagement.dto.BookDTO;
import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.enums.SearchType;
import com.sepehr.librarymanagement.mapper.EntityDTOMapper;
import com.sepehr.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final EntityDTOMapper entityDTOMapper;

    @Autowired
    public BookController(BookService bookService, EntityDTOMapper entityDTOMapper) {
        this.bookService = bookService;
        this.entityDTOMapper = entityDTOMapper;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book();
        entityDTOMapper.mapBookDTOToBook(bookDTO, book);
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            entityDTOMapper.mapBookDTOToBook(bookDTO, existingBook);
            Book updatedBook = bookService.updateBook(id, existingBook);
            if (updatedBook != null) {
                return ResponseEntity.ok(updatedBook);
            }
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Book> searchBooks(@RequestParam String name, @RequestParam SearchType searchType) {
        if (name == null || searchType == null) {
            return ResponseEntity.badRequest().body(null); // یا هر پیام دلخواه
        }

        Book foundBook = bookService.searchByNameAndType(name, searchType);
        if (foundBook != null) {
            return ResponseEntity.ok(foundBook);
        } else {
            return ResponseEntity.notFound().build(); // یا هر پیام دلخواه
        }
    }


}