package com.sepehr.librarymanagement.mapper;

import com.sepehr.librarymanagement.dto.BookDTO;
import com.sepehr.librarymanagement.dto.MemberDTO;
import com.sepehr.librarymanagement.entity.AuthorTranslator;
import com.sepehr.librarymanagement.entity.Book;
import com.sepehr.librarymanagement.entity.BookAuthorTranslator;
import com.sepehr.librarymanagement.entity.Member;
import com.sepehr.librarymanagement.repository.AuthorTranslatorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityDTOMapper {
    private final AuthorTranslatorRepository authorTranslatorRepository;

    public EntityDTOMapper(AuthorTranslatorRepository authorTranslatorRepository) {
        this.authorTranslatorRepository = authorTranslatorRepository;
    }

    public void mapBookDTOToBook(BookDTO bookDTO, Book book) {
        book.setTitle(bookDTO.getTitle());
        book.setBookNumber(bookDTO.getBookNumber());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setPublishers(bookDTO.getPublishers());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setEditionNumber(bookDTO.getEditionNumber());

        List<BookAuthorTranslator> bookAuthorTranslators = new ArrayList<>();
        for (Long authorTranslatorId : bookDTO.getAuthorTranslatorIds()) {
            AuthorTranslator authorTranslator = authorTranslatorRepository.findById(authorTranslatorId).orElse(null);
            if (authorTranslator != null) {
                BookAuthorTranslator bookAuthorTranslator = new BookAuthorTranslator();
                bookAuthorTranslator.setBook(book);
                bookAuthorTranslator.setAuthorTranslator(authorTranslator);
                bookAuthorTranslators.add(bookAuthorTranslator);
            }
        }
        book.setBookAuthorTranslators(bookAuthorTranslators);

    }

    public void mapMemberDTOToMember(MemberDTO memberDTO, Member member) {
        member.setMembershipNumber(memberDTO.getMembershipNumber());
        member.setUniqueCode(memberDTO.getUniqueCode());
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());
        member.setBirthDate(memberDTO.getBirthDate());
        member.setMembershipDate(memberDTO.getMembershipDate());
        member.setFatherName(memberDTO.getFatherName());
        member.setGender(memberDTO.getGender());
        member.setIsActive(memberDTO.getIsActive());
    }


}
