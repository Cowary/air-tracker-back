package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.rest.dto.response.TvDtoRs;

@Data
@AllArgsConstructor
public class TvRs implements MediaRs {

    private TvDtoRs tv;
    private String posterUrl;
}
