package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.rest.dto.response.RanobeDtoRs;

@Data
@AllArgsConstructor
public class RanobeRs implements MediaRs {

    private RanobeDtoRs ranobe;
    private String posterUrl;


}
