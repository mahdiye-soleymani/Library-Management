package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.exception.BorrowLimitExceededException;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);

    Member borrowBook(Long memberId, Long bookId) throws BorrowLimitExceededException;

    void returnBook(Long memberId, Long bookId);

    List<Member> searchMembersByFirstName(String firstName);

    List<Member> searchMembersByLastName(String lastName);

    List<Member> searchMembersByFirstNameAndLastName(String firstName, String lastName);

    List<Member> searchMembersByMembershipDate(LocalDate membershipDate);

}