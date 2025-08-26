package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.rest.dto.response.AnimeDtoRs;

@Data
@AllArgsConstructor
public class AnimeRs implements MediaRs {
    private AnimeDtoRs media;
    private String posterUrl;
}
