package org.cowary.arttrackerback.rest.converter;

import org.cowary.arttrackerback.entity.book.Book;
import org.cowary.arttrackerback.rest.dto.request.BookDtoRq;
import org.cowary.arttrackerback.rest.dto.response.BookDtoRs;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for BookDtoConverter class that handles conversion between Book entity
 * and its corresponding DTOs (Data Transfer Objects) for REST API communication.
 * Tests both entity-to-DTO and DTO-to-entity conversion methods to ensure data integrity.
 */
class BookDtoConverterTest {

    /**
     * Creates a sample Book entity for testing with predefined values.
     * @return Book entity with test data
     */
    private Book createBook() {
        return Book.builder()
                .id(1L)
                .title("Test Book")
                .endDate(LocalDate.of(2024, 12, 31))
                .releaseDate(LocalDate.of(2024, 1, 15))
                .releaseYear(2024)
                .score(9)
                .status("Completed")
                .author("John Doe")
                .lastUpd(LocalDate.of(2024, 11, 28))
                .usrId(100L)
                .type("Book")
                .build();
    }

    /**
     * Creates a sample BookDtoRq for testing with predefined values.
     * @return BookDtoRq with test data
     */
    private BookDtoRq createBookDtoRq() {
        return BookDtoRq.builder()
                .id(1L)
                .title("Test Book")
                .endDate(LocalDate.of(2024, 12, 31))
                .releaseDate(LocalDate.of(2024, 1, 15))
                .releaseYear(2024)
                .score(9)
                .status("Completed")
                .author("John Doe")
                .lastUpd(LocalDate.of(2024, 11, 28))
                .usrId(100L)
                .type("Book")
                .build();
    }

    @Test
    void convert_BookToBookDtoRs_ShouldConvertAllFieldsCorrectly() {
        // Given
        Book book = createBook();

        // When
        BookDtoRs dto = BookDtoConverter.convert(book);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("id", book.getId())
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("endDate", book.getEndDate())
                .hasFieldOrPropertyWithValue("releaseDate", book.getReleaseDate())
                .hasFieldOrPropertyWithValue("releaseYear", book.getReleaseYear())
                .hasFieldOrPropertyWithValue("score", book.getScore())
                .hasFieldOrPropertyWithValue("status", book.getStatus())
                .hasFieldOrPropertyWithValue("author", book.getAuthor())
                .hasFieldOrPropertyWithValue("usrId", book.getUsrId());
    }

    @Test
    void convert_BookDtoRqToBook_ShouldConvertAllFieldsCorrectly() {
        // Given
        BookDtoRq dtoRq = createBookDtoRq();

        // When
        Book book = BookDtoConverter.convert(dtoRq);

        // Then
        assertThat(book)
                .hasFieldOrPropertyWithValue("id", dtoRq.getId())
                .hasFieldOrPropertyWithValue("title", dtoRq.getTitle())
                .hasFieldOrPropertyWithValue("endDate", dtoRq.getEndDate())
                .hasFieldOrPropertyWithValue("releaseDate", dtoRq.getReleaseDate())
                .hasFieldOrPropertyWithValue("releaseYear", dtoRq.getReleaseYear())
                .hasFieldOrPropertyWithValue("score", dtoRq.getScore())
                .hasFieldOrPropertyWithValue("status", dtoRq.getStatus())
                .hasFieldOrPropertyWithValue("author", dtoRq.getAuthor())
                .hasFieldOrPropertyWithValue("usrId", dtoRq.getUsrId());
    }

    @Test
    void convert_BookToBookDtoRs_WhenBookIsNull_ShouldThrowException() {
        // When & Then
        assertThatThrownBy(() -> BookDtoConverter.convert((Book) null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void convert_BookDtoRqToBook_WhenDtoRqIsNull_ShouldThrowException() {
        // When & Then
        assertThatThrownBy(() -> BookDtoConverter.convert((BookDtoRq) null))
                .isInstanceOf(NullPointerException.class);
    }
}