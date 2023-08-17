package com.sepehr.librarymanagement.repository;

import com.sepehr.librarymanagement.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

}