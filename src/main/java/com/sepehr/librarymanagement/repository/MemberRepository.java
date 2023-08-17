package com.sepehr.librarymanagement.repository;

import com.sepehr.librarymanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByFirstName(String firstName);

    List<Member> findByLastName(String lastName);

    List<Member> findByFirstNameAndLastName(String firstName, String lastName);

    List<Member> findByMembershipDate(LocalDate membershipDate);

}
