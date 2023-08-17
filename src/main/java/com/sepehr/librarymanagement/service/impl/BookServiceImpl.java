package com.sepehr.librarymanagement.service.impl;


import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.enums.SearchType;
import com.sepehr.librarymanagement.repository.BookRepository;
import com.sepehr.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean isBookAvailableForBorrow(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return book != null && book.getBorrower() == null;

    }

    @Override
    public Book searchByNameAndType(String name, SearchType searchType) {

        switch (searchType) {
            case TITLE:
                return bookRepository.findByTitle(name);
            case AUTHOR:
                return bookRepository.findByAuthorsFirstNameContainingOrAuthorsLastNameContaining(name, name);
            case TRANSLATOR:
                return bookRepository.findByTranslatorsFirstNameContainingOrTranslatorsLastNameContaining(name, name);
            default:
                return null;
        }
    }

}