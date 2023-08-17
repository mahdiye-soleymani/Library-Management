package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.enums.SearchType;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    boolean isBookAvailableForBorrow(Long bookId);

    Book searchByNameAndType(String name, SearchType searchType);

    void setBookBorrower(Long bookId, Member borrower);

}