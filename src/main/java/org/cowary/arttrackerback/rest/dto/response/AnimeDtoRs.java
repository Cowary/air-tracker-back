package org.cowary.arttrackerback.rest.dto.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimeDtoRs {
    Long id;
    String originalTitle;
    String title;
    Integer episodes;
    @NotBlank
    String status;
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    LocalDate releaseDate;
    Integer releaseYear;
    Integer shikiId;
    Integer duration;
    Integer episodesEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    LocalDate lastUpd;
    Long usrId;
    String type = "Anime";
}
