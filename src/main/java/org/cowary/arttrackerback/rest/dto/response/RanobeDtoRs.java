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
public class RanobeDtoRs {
    Long id;
    String originalTitle;
    String title;
    Integer volumes;
    Integer score;
    String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate releaseDate;
    Integer releaseYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Integer shikiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate lastUpd;
    Long usrId;
    LocalDate endDate;
    String type = "Ranobe";
}
