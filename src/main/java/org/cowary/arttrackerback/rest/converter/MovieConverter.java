package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.rest.dto.response.MovieDtoRs;

@UtilityClass
public class MovieConverter {

    public static MovieDtoRs convert(Movie movie) {
        return MovieDtoRs.builder()
                .id(movie.getId())
                .originalTitle(movie.getOriginalTitle())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .duration(movie.getDuration())
                .status(movie.getStatus())
                .score(movie.getScore())
                .endDate(movie.getEndDate())
                .usrId(movie.getUsrId())
                .build();
    }

    public static Movie convert(MovieDtoRs movieDtoRs) {
        return Movie.builder()
                .id(movieDtoRs.getId())
                .originalTitle(movieDtoRs.getOriginalTitle())
                .title(movieDtoRs.getTitle())
                .releaseYear(movieDtoRs.getReleaseYear())
                .duration(movieDtoRs.getDuration())
                .status(movieDtoRs.getStatus())
                .score(movieDtoRs.getScore())
                .endDate(movieDtoRs.getEndDate())
                .usrId(movieDtoRs.getUsrId())
                .build();
    }

}
