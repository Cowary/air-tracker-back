package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.book.Book;
import org.cowary.arttrackerback.rest.dto.request.BookDtoRq;
import org.cowary.arttrackerback.rest.dto.response.BookDtoRs;

@UtilityClass
public class BookDtoConverter {

    public static BookDtoRs convert(Book book) {
        return BookDtoRs.builder()
                .id(book.getId())
                .title(book.getTitle())
                .endDate(book.getEndDate())
                .releaseDate(book.getReleaseDate())
                .releaseYear(book.getReleaseYear())
                .score(book.getScore())
                .status(book.getStatus())
                .author(book.getAuthor())
                .usrId(book.getUsrId())
                .build();
    }

    public static Book convert(BookDtoRq source) {
        return Book.builder()
                .id(source.getId())
                .title(source.getTitle())
                .endDate(source.getEndDate())
                .releaseDate(source.getReleaseDate())
                .releaseYear(source.getReleaseYear())
                .score(source.getScore())
                .status(source.getStatus())
                .author(source.getAuthor())
                .usrId(source.getUsrId())
                .build();
    }
}
