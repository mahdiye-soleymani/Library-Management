package com.sepehr.librarymanagement.controller;

import com.sepehr.librarymanagement.dto.MemberDTO;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.exception.BorrowLimitExceededException;
import com.sepehr.librarymanagement.exception.InactiveMemberException;
import com.sepehr.librarymanagement.exception.NotFoundException;
import com.sepehr.librarymanagement.mapper.EntityDTOMapper;
import com.sepehr.librarymanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final EntityDTOMapper entityDTOMapper;

    @Autowired
    public MemberController(MemberService memberService, EntityDTOMapper entityDTOMapper) {
        this.memberService = memberService;
        this.entityDTOMapper = entityDTOMapper;
    }


    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody MemberDTO memberDTO) {
        Member member = new Member();
        entityDTOMapper.mapMemberDTOToMember(memberDTO, member);
        Member createdMember = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        Member existingMember = memberService.getMemberById(id);
        if (existingMember != null) {
            entityDTOMapper.mapMemberDTOToMember(memberDTO, existingMember);
            Member updatedMember = memberService.updateMember(id, existingMember);
            if (updatedMember != null) {
                return ResponseEntity.ok(updatedMember);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{memberId}/borrow/{bookId}")
    public ResponseEntity<Member> borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        try {
            Member borrowedMember = memberService.borrowBook(memberId, bookId);
            return ResponseEntity.ok(borrowedMember);
        } catch (BorrowLimitExceededException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InactiveMemberException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{memberId}/return/{bookId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        try {
            memberService.returnBook(memberId, bookId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(@RequestParam(required = false) String firstName,
                                                      @RequestParam(required = false) String lastName) {
        if (firstName != null && lastName != null) {
            // Search by both first name and last name
            List<Member> members = memberService.searchMembersByFirstNameAndLastName(firstName, lastName);
            return ResponseEntity.ok(members);
        } else if (firstName != null) {
            // Search by first name
            List<Member> members = memberService.searchMembersByFirstName(firstName);
            return ResponseEntity.ok(members);
        } else if (lastName != null) {
            // Search by last name
            List<Member> members = memberService.searchMembersByLastName(lastName);
            return ResponseEntity.ok(members);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/searchByMembershipDate")
    public ResponseEntity<List<Member>> searchMembersByMembershipDate(@RequestParam LocalDate membershipDate) {
        List<Member> members = memberService.searchMembersByMembershipDate(membershipDate);
        return ResponseEntity.ok(members);
    }
}