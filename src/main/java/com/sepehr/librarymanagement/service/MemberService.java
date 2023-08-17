package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.exception.BorrowLimitExceededException;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);

    Member borrowBook(Long memberId, Long bookId) throws BorrowLimitExceededException;

    void returnBook(Long memberId, Long bookId);

}