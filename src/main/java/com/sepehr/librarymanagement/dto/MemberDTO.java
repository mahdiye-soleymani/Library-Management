package com.sepehr.librarymanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDTO {
    private String membershipNumber;
    private String uniqueCode;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate membershipDate;
    private String fatherName;
    private String gender;
    private Boolean isActive;
}
