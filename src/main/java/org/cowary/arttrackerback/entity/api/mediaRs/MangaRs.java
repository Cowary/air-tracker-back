package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cowary.arttrackerback.rest.dto.response.MangaDtoRs;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MangaRs implements MediaRs {
    private MangaDtoRs media;
    private String posterUrl;
}
