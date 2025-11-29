package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.rest.dto.response.TvSeasonDtoRs;

@Data
@AllArgsConstructor
public class TvRs implements MediaRs {
    private TvSeasonDtoRs media;
    private String posterUrl;
}
