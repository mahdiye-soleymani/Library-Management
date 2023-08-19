package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.enums.SearchType;

import java.util.List;

public interface BookService {

    /**
     * Get a list of all books.
     *
     * @return List of books
     */
    List<Book> getAllBooks();

    /**
     * Get a book by its ID.
     *
     * @param id ID of the book
     * @return Book with the specified ID
     */
    Book getBookById(Long id);

    /**
     * Create a new book.
     *
     * @param book The book to be created
     * @return Created book
     */
    Book createBook(Book book);

    /**
     * Update an existing book.
     *
     * @param id   ID of the book to be updated
     * @param book Updated book information
     * @return Updated book
     */
    Book updateBook(Long id, Book book);

    /**
     * Delete a book by its ID.
     *
     * @param id ID of the book to be deleted
     */
    void deleteBook(Long id);

    /**
     * Check if a book is available for borrowing.
     *
     * @param bookId ID of the book
     * @return True if the book is available, false otherwise
     */
    boolean isBookAvailableForBorrow(Long bookId);

    /**
     * Search for a book by name and search type.
     *
     * @param name       Name to search for
     * @param searchType Type of search (e.g., title, author, translator)
     * @return Found book or null if not found
     */
    Book searchByNameAndType(String name, SearchType searchType);

    /**
     * Set the borrower for a book.
     *
     * @param bookId  ID of the book
     * @param borrower Member who borrows the book
     */
    void setBookBorrower(Long bookId, Member borrower);
}
