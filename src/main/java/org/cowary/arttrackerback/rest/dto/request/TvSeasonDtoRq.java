package org.cowary.arttrackerback.rest.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.cowary.arttrackerback.rest.dto.response.TvDtoRs;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TvSeasonDtoRq {
    Long id;
    String title;
    String originalTitle;
    Integer number;
    Integer episodes;
    Integer episodesEnd;
    String status;
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate lastUpd;
    Integer releaseYear;
    TvDtoRs tv;
    Long usrId;
    Integer integrationId;
    String type = "Tv";
}