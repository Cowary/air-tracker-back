package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.rest.dto.request.MovieDtoRq;
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
                .integrationId(movie.getIntegrationId())
                .build();
    }

    public static MovieDtoRq convertRq(Movie movie) {
        return MovieDtoRq.builder()
                .id(movie.getId())
                .originalTitle(movie.getOriginalTitle())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .duration(movie.getDuration())
                .status(movie.getStatus())
                .score(movie.getScore())
                .endDate(movie.getEndDate())
                .usrId(movie.getUsrId())
                .integrationId(movie.getIntegrationId())
                .build();
    }

    public static Movie convert(MovieDtoRq movieDtoRq) {
        return Movie.builder()
                .id(movieDtoRq.getId())
                .originalTitle(movieDtoRq.getOriginalTitle())
                .title(movieDtoRq.getTitle())
                .releaseYear(movieDtoRq.getReleaseYear())
                .duration(movieDtoRq.getDuration())
                .status(movieDtoRq.getStatus())
                .score(movieDtoRq.getScore())
                .endDate(movieDtoRq.getEndDate())
                .usrId(movieDtoRq.getUsrId())
                .integrationId(movieDtoRq.getIntegrationId())
                .build();
    }

}
