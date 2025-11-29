package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.rest.dto.response.RanobeVolumeDtoRs;

@Data
@AllArgsConstructor
public class RanobeRs implements MediaRs {
    private RanobeVolumeDtoRs media;
    private String posterUrl;
}
