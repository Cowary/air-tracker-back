package org.cowary.arttrackerback.rest.converter;

import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.rest.dto.request.AnimeDtoRq;
import org.cowary.arttrackerback.rest.dto.response.AnimeDtoRs;

@UtilityClass
public class AnimeDtoConverter {

    public AnimeDtoRs convert(@NotNull Anime anime) {
        return AnimeDtoRs.builder()
                .id(anime.getId())
                .originalTitle(anime.getOriginalTitle())
                .title(anime.getTitle())
                .episodes(anime.getEpisodes())
                .status(anime.getStatus())
                .score(anime.getScore())
                .endDate(anime.getEndDate())
                .releaseDate(anime.getReleaseDate())
                .releaseYear(anime.getReleaseYear())
                .duration(anime.getDuration())
                .episodesEnd(anime.getEpisodesEnd())
                .usrId(anime.getUsrId())
                .shikiId(anime.getShikiId())
                .build();
    }

    public Anime convert(@NotNull AnimeDtoRq source) {
        return Anime.builder()
                .id(source.getId())
                .originalTitle(source.getOriginalTitle())
                .title(source.getTitle())
                .episodes(source.getEpisodes())
                .status(source.getStatus())
                .score(source.getScore())
                .endDate(source.getEndDate())
                .releaseDate(source.getReleaseDate())
                .releaseYear(source.getReleaseDate().getYear())
                .shikiId(source.getShikiId())
                .duration(source.getDuration())
                .episodesEnd(source.getEpisodesEnd())
                .usrId(source.getUsrId())
                .build();
    }

}
