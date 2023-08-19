package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.BorrowedBook;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.exception.BorrowLimitExceededException;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {

    /**
     * Get a list of all members.
     *
     * @return List of members
     */
    List<Member> getAllMembers();

    /**
     * Get a member by its ID.
     *
     * @param id ID of the member
     * @return Member with the specified ID
     */
    Member getMemberById(Long id);

    /**
     * Create a new member.
     *
     * @param member The member to be created
     * @return Created member
     */
    Member createMember(Member member);

    /**
     * Update an existing member.
     *
     * @param id     ID of the member to be updated
     * @param member Updated member information
     * @return Updated member
     */
    Member updateMember(Long id, Member member);

    /**
     * Delete a member by its ID.
     *
     * @param id ID of the member to be deleted
     */
    void deleteMember(Long id);

    /**
     * Borrow a book for a member.
     *
     * @param memberId ID of the member borrowing the book
     * @param bookId   ID of the book to be borrowed
     * @return BorrowedBook object representing the borrowed book
     * @throws BorrowLimitExceededException if the member has reached the borrowing limit
     */
    BorrowedBook borrowBook(Long memberId, Long bookId) throws BorrowLimitExceededException;

    /**
     * Return a borrowed book.
     *
     * @param borrowedBookId ID of the borrowed book
     */
    void returnBook(Long borrowedBookId);

    /**
     * Search for members by their first name.
     *
     * @param firstName First name of the members to search for
     * @return List of members with the specified first name
     */
    List<Member> searchMembersByFirstName(String firstName);

    /**
     * Search for members by their last name.
     *
     * @param lastName Last name of the members to search for
     * @return List of members with the specified last name
     */
    List<Member> searchMembersByLastName(String lastName);

    /**
     * Search for members by their first name and last name.
     *
     * @param firstName First name of the members to search for
     * @param lastName  Last name of the members to search for
     * @return List of members with the specified first name and last name
     */
    List<Member> searchMembersByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Search for members by their membership date.
     *
     * @param membershipDate Membership date of the members to search for
     * @return List of members with the specified membership date
     */
    List<Member> searchMembersByMembershipDate(LocalDate membershipDate);
}
