package org.cowary.arttrackerback.integration.model.kin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KinFilmModel {

    private int kinopoiskId;
    private String nameRu;
    private String nameEn;
    private String nameOriginal;
    private String posterUrl;
    private int year;
    private boolean serial;
    private int filmLength;
}
