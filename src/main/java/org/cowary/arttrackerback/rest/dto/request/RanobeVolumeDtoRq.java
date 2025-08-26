package org.cowary.arttrackerback.rest.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.cowary.arttrackerback.rest.dto.response.RanobeDtoRs;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RanobeVolumeDtoRq {
    Long id;
    String title;
    String originalTitle;
    Integer number;
    String status;
    Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    RanobeDtoRs ranobe;
    Long usrId;
    Integer integrationId;
    LocalDate releaseDate;
    String type = "Ranobe";
}
