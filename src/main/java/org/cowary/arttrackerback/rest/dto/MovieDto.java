package org.cowary.arttrackerback.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {
    Long id;
    String originalTitle;
    @NotNull
    @NotBlank
    String title;
    @NotNull
    Integer releaseYear;
    Integer duration;
    @NotNull
    @NotBlank
    String status;
    @NotNull
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate lastUpd;
    Long usrId;
    Integer integrationId;
    String type = "Movie";
}
