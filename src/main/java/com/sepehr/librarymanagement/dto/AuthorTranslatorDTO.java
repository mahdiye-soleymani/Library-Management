package com.sepehr.librarymanagement.dto;
import com.sepehr.librarymanagement.enums.AuthorTranslatorType;
import lombok.Data;

@Data
public class AuthorTranslatorDTO {
    private String name;
    private AuthorTranslatorType type;
}
