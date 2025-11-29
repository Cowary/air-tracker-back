package org.cowary.arttrackerback.integration.model.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultModel {

    private boolean adult;
    private int id;
    @JsonProperty("original_title")
    private String originalTitle;
    private String title;
    @JsonProperty("vote_average")
    private int voteAverage;
}
