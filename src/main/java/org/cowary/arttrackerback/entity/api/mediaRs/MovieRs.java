package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cowary.arttrackerback.rest.dto.response.MovieDtoRs;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRs implements MediaRs {

    private MovieDtoRs movie;
    private String posterUrl;
}
