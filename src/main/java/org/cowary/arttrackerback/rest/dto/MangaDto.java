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
public class MangaDto {
    Long id;
    String originalTitle;
    String title;
    Integer volumes;
    Integer chapters;
    String status;
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate releaseDate;
    Integer releaseYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    Integer shikiId;
    Integer volumesEnd;
    Integer chaptersEnd;
    Long usrId;
    String type = "Manga";
}
