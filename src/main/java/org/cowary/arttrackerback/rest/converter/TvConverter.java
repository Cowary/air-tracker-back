package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.entity.tv.TvSeason;
import org.cowary.arttrackerback.rest.dto.request.TvSeasonDtoRq;
import org.cowary.arttrackerback.rest.dto.response.TvDtoRs;
import org.cowary.arttrackerback.rest.dto.response.TvSeasonDtoRs;

@UtilityClass
public class TvConverter {

    public static Tv convert(TvDtoRs tvDtoRs) {
        return Tv.builder()
                .id(tvDtoRs.getId())
                .originalTitle(tvDtoRs.getOriginalTitle())
                .title(tvDtoRs.getTitle())
                .releaseYear(tvDtoRs.getReleaseYear())
                .score(tvDtoRs.getScore())
                .seasons(tvDtoRs.getSeasons())
                .usrId(tvDtoRs.getUsrId())
                .integrationId(tvDtoRs.getIntegrationId())
                .build();
    }

    public static TvDtoRs convert(Tv tv) {
        return TvDtoRs.builder()
                .id(tv.getId())
                .originalTitle(tv.getOriginalTitle())
                .title(tv.getTitle())
                .releaseYear(tv.getReleaseYear())
                .score(tv.getScore())
                .seasons(tv.getSeasons())
                .usrId(tv.getUsrId())
                .integrationId(tv.getIntegrationId())
                .build();
    }

    public static TvSeason convert(TvSeasonDtoRq source) {
        return TvSeason.builder()
                .id(source.getId())
                .title(source.getTitle())
                .originalTitle(source.getOriginalTitle())
                .number(source.getNumber())
                .episodes(source.getEpisodes())
                .episodesEnd(source.getEpisodesEnd())
                .status(source.getStatus())
                .score(source.getScore())
                .endDate(source.getEndDate())
//                .tv(convert(tvSeasonDtoRs.getTv()))
                .usrId(source.getUsrId())
                .releaseYear(source.getReleaseYear())
                .integrationId(source.getIntegrationId())
                .build();
    }

    public static TvSeasonDtoRs convert(TvSeason tvSeason) {
        return TvSeasonDtoRs.builder()
                .id(tvSeason.getId())
                .title(tvSeason.getTitle())
                .number(tvSeason.getNumber())
                .episodes(tvSeason.getEpisodes())
                .episodesEnd(tvSeason.getEpisodesEnd())
                .status(tvSeason.getStatus())
                .score(tvSeason.getScore())
                .endDate(tvSeason.getEndDate())
//                .tv(convert(tvSeason.getTv()))
                .usrId(tvSeason.getUsrId())
                .releaseYear(tvSeason.getReleaseYear())
                .build();
    }
}
