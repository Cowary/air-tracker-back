package org.cowary.arttrackerback.rest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    Long id;
    String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate releaseDate;
    Integer releaseYear;
    Integer score;
    String status;
    String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate lastUpd;
    Long usrId;
    String type = "Book";
}
