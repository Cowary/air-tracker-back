package org.cowary.arttrackerback.rest.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameDtoRs {

    Long id;
    String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate releaseDate;
    Integer releaseYear;
    Integer score;
    String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UpdateTimestamp
    LocalDate lastUpd;
    Long usrId;
    String type = "Game";
}
