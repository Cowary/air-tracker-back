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
public class TvDtoRs {
    Long id;
    String originalTitle;
    String title;
    Integer releaseYear;
    Integer score;
    Integer seasons;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate lastUpd;
    Long usrId;
    Integer integrationId;
    String type = "Tv";
}
