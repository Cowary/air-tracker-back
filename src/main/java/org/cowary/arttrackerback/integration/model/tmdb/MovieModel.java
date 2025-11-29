package org.cowary.arttrackerback.integration.model.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MovieModel {

    private int id;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("production_companies")
    private ProductionCompanyModel[] productionCompanies;
    @JsonProperty("release_date")
    private Date releaseDate;
    private int runtime;
    private String title;

}
