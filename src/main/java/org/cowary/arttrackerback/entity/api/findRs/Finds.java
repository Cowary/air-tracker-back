package org.cowary.arttrackerback.entity.api.findRs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Finds {

    private String nameEn;
    private String nameRu;
    private String score;
    private Integer episodes;
    private Integer year;
    private Integer integrationId;
}
