package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.manga.Manga;
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

    public static Manga convert(MangaDtoRs mangaDtoRs) {
        return Manga.builder()
                .id(mangaDtoRs.getId())
                .originalTitle(mangaDtoRs.getOriginalTitle())
                .title(mangaDtoRs.getTitle())
                .volumes(mangaDtoRs.getVolumes())
                .chapters(mangaDtoRs.getChapters())
                .status(mangaDtoRs.getStatus())
                .score(mangaDtoRs.getScore())
                .releaseDate(mangaDtoRs.getReleaseDate())
                .releaseYear(mangaDtoRs.getReleaseYear())
                .endDate(mangaDtoRs.getEndDate())
                .shikiId(mangaDtoRs.getShikiId())
                .volumesEnd(mangaDtoRs.getVolumesEnd())
                .chaptersEnd(mangaDtoRs.getChaptersEnd())
                .build();
    }
}
