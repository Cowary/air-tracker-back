package org.cowary.arttrackerback.rest.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class AnimeDtoRq {
    Long id;
    @NotBlank
    String originalTitle;
    @NotBlank
    String title;
    Integer episodes;
    @NotBlank
    String status;
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    LocalDate endDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    LocalDate releaseDate;
    Integer releaseYear;
    @NotNull
    Integer shikiId;
    Integer duration;
    Integer episodesEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    LocalDate lastUpd;
    @NotNull
    Long usrId;
}
