package org.cowary.arttrackerback.rest.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TvSeasonDtoRs {
    Long id;
    String title;
    Integer number;
    Integer episodes;
    Integer episodesEnd;
    String status;
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate lastUpd;
    TvDtoRs tv;
    Long usrId;
    String type = "Tv";
}
