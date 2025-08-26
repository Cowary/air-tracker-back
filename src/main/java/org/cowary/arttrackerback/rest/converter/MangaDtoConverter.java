package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.rest.dto.request.MangaDtoRq;
import org.cowary.arttrackerback.rest.dto.response.MangaDtoRs;

@UtilityClass
public class MangaDtoConverter {

    public static MangaDtoRs convert(Manga manga) {
        return MangaDtoRs.builder()
                .id(manga.getId())
                .originalTitle(manga.getOriginalTitle())
                .title(manga.getTitle())
                .volumes(manga.getVolumes())
                .chapters(manga.getChapters())
                .status(manga.getStatus())
                .score(manga.getScore())
                .releaseDate(manga.getReleaseDate())
                .releaseYear(manga.getReleaseYear())
                .endDate(manga.getEndDate())
                .shikiId(manga.getShikiId())
                .volumesEnd(manga.getVolumesEnd())
                .chaptersEnd(manga.getChaptersEnd())
                .build();
    }

    public static Manga convert(MangaDtoRq source) {
        return Manga.builder()
                .id(source.getId())
                .originalTitle(source.getOriginalTitle())
                .title(source.getTitle())
                .volumes(source.getVolumes())
                .chapters(source.getChapters())
                .status(source.getStatus())
                .score(source.getScore())
                .releaseDate(source.getReleaseDate())
                .releaseYear(source.getReleaseYear())
                .endDate(source.getEndDate())
                .shikiId(source.getShikiId())
                .volumesEnd(source.getVolumesEnd())
                .chaptersEnd(source.getChaptersEnd())
                .build();
    }
}
