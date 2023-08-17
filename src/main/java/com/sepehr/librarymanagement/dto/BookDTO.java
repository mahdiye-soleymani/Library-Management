
package com.sepehr.librarymanagement.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO {
    private String title;
    private String bookNumber;
    private LocalDate publicationDate;
    private String publishers;
    private int publicationYear;
    private int editionNumber;
    private List<Long> authorTranslatorIds;
}
