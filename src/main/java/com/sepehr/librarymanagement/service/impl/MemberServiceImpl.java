package com.sepehr.librarymanagement.service.impl;


import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.exception.BorrowLimitExceededException;
import com.sepehr.librarymanagement.exception.InactiveMemberException;
import com.sepehr.librarymanagement.exception.NotFoundException;
import com.sepehr.librarymanagement.repository.BookRepository;
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
    private final BookRepository bookRepository;

    public MemberServiceImpl(MemberRepository memberRepository, BookService bookService, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
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
    public Member borrowBook(Long memberId, Long bookId) throws BorrowLimitExceededException {
        Member member = memberRepository.findById(memberId).orElse(null);
        Book book = bookService.getBookById(bookId);

        if (member != null && book != null) {
            if (member.getIsActive()) {
                if (member.getBorrowedBooks().size() < 2 && bookService.isBookAvailableForBorrow(bookId)) {
                    member.getBorrowedBooks().add(book);
                    book.setBorrower(member);
                    memberRepository.save(member);
                    bookRepository.save(book);
                    return member;
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
    public void returnBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Book book = bookService.getBookById(bookId);

        if (member != null && book != null && book.getBorrower() != null) {
            if (member.getBorrowedBooks().contains(book)) {
                member.getBorrowedBooks().remove(book);
                book.setBorrower(null);
                memberRepository.save(member);
                bookRepository.save(book);
            } else {
                throw new NotFoundException("The book is not borrowed by the member.");
            }
        } else {
            throw new NotFoundException("Member or book not found.");
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