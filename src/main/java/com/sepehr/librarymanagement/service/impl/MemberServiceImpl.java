package com.sepehr.librarymanagement.service.impl;


import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.entity.BorrowedBook;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.exception.BorrowLimitExceededException;
import com.sepehr.librarymanagement.exception.InactiveMemberException;
import com.sepehr.librarymanagement.exception.NotFoundException;
import com.sepehr.librarymanagement.repository.BorrowedBookRepository;
import com.sepehr.librarymanagement.repository.MemberRepository;
import com.sepehr.librarymanagement.service.BookService;
import com.sepehr.librarymanagement.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BookService bookService;

    private final BorrowedBookRepository borrowedBookRepository;

    public MemberServiceImpl(MemberRepository memberRepository, BookService bookService, BorrowedBookRepository borrowedBookRepository) {
        this.memberRepository = memberRepository;
        this.bookService = bookService;

        this.borrowedBookRepository = borrowedBookRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        if (memberRepository.existsById(id)) {
            member.setId(id);
            return memberRepository.save(member);
        }
        return null;
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public BorrowedBook borrowBook(Long memberId, Long bookId) throws BorrowLimitExceededException {
        Member member = memberRepository.findById(memberId).orElse(null);
        Book book = bookService.getBookById(bookId);

        if (member != null && book != null) {
            if (member.getIsActive()) {
                if (member.getBorrowedBooks().size() < 2 && bookService.isBookAvailableForBorrow(bookId)) {
                    BorrowedBook borrowedBook = new BorrowedBook();
                    borrowedBook.setMember(member);
                    borrowedBook.setBook(book);
                    borrowedBook.setBorrowDate(LocalDate.now());
                    borrowedBookRepository.save(borrowedBook);

                    member.getBorrowedBooks().add(borrowedBook);
                    memberRepository.save(member);

                    bookService.setBookBorrower(bookId, member);
                    return borrowedBook;
                } else {
                    throw new BorrowLimitExceededException("The member has reached the borrowing limit or the book is not available.");
                }
            } else {
                throw new InactiveMemberException("The member is not active.");
            }
        } else {
            throw new NotFoundException("Member or book not found.");
        }
    }

    @Override
    public void returnBook(Long borrowedBookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId).orElse(null);

        if (borrowedBook != null) {
            Member member = borrowedBook.getMember();
            Book book = borrowedBook.getBook();

            borrowedBook.setReturnDate(LocalDate.now());
            borrowedBookRepository.save(borrowedBook);

            member.getBorrowedBooks().remove(borrowedBook);
            memberRepository.save(member);

            bookService.setBookBorrower(book.getId(), null);
        } else {
            throw new NotFoundException("Borrowed book not found.");
        }
    }


    @Override
    public List<Member> searchMembersByFirstName(String firstName) {
        return memberRepository.findByFirstName(firstName);
    }

    @Override
    public List<Member> searchMembersByLastName(String lastName) {
        return memberRepository.findByLastName(lastName);
    }

    @Override
    public List<Member> searchMembersByFirstNameAndLastName(String firstName, String lastName) {
        return memberRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Member> searchMembersByMembershipDate(LocalDate membershipDate) {
        return memberRepository.findByMembershipDate(membershipDate);
    }
}