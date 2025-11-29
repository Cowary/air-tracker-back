/**
 * Unit tests for AnimeDtoConverter class that handles conversion between Anime entity
 * and its corresponding DTOs (Data Transfer Objects) for REST API communication.
 * Tests both entity-to-DTO and DTO-to-entity conversion methods to ensure data integrity.
 */
package org.cowary.arttrackerback.rest.converter;

/**
 * Unit tests for AnimeDtoConverter class that handles conversion between Anime entity
 * and its corresponding DTOs (Data Transfer Objects) for REST API communication.
 * Tests both entity-to-DTO and DTO-to-entity conversion methods to ensure data integrity.
 */

import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.rest.dto.request.AnimeDtoRq;
import org.cowary.arttrackerback.rest.dto.response.AnimeDtoRs;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for AnimeDtoConverter utility class.
 * Verifies proper conversion between Anime entity and DTO objects.
 */
class AnimeDtoConverterTest {

    /**
     * Creates a sample Anime entity for testing with predefined values.
     * @return Anime entity with test data
     */
    private Anime createAnime() {
        return Anime.builder()
                .id(1L)
                .originalTitle("Original Title")
                .title("Test Anime")
                .episodes(12)
                .status("Watching")
                .score(8)
                .endDate(LocalDate.of(2024, 12, 31))
                .releaseDate(LocalDate.of(2024, 1, 15))
                .releaseYear(2024)
                .shikiId(999)
                .duration(24)
                .episodesEnd(10)
                .usrId(100L)
                .build();
    }

    /**
     * Creates a sample AnimeDtoRq for testing with predefined values.
     * @return AnimeDtoRq with test data
     */
    private AnimeDtoRq createAnimeDtoRq() {
        return AnimeDtoRq.builder()
                .id(1L)
                .originalTitle("Original Title")
                .title("Test Anime")
                .episodes(12)
                .status("Watching")
                .score(8)
                .endDate(LocalDate.of(2024, 12, 31))
                .releaseDate(LocalDate.of(2024, 1, 15))
                .shikiId(999)
                .duration(24)
                .episodesEnd(10)
                .usrId(100L)
                .build();
    }

    @Test
    void convert_AnimeToAnimeDtoRs_ShouldConvertAllFieldsCorrectly() {
        // Given
        Anime anime = createAnime();

        // When
        AnimeDtoRs dto = AnimeDtoConverter.convert(anime);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("id", anime.getId())
                .hasFieldOrPropertyWithValue("originalTitle", anime.getOriginalTitle())
                .hasFieldOrPropertyWithValue("title", anime.getTitle())
                .hasFieldOrPropertyWithValue("episodes", anime.getEpisodes())
                .hasFieldOrPropertyWithValue("status", anime.getStatus())
                .hasFieldOrPropertyWithValue("score", anime.getScore())
                .hasFieldOrPropertyWithValue("endDate", anime.getEndDate())
                .hasFieldOrPropertyWithValue("releaseDate", anime.getReleaseDate())
                .hasFieldOrPropertyWithValue("releaseYear", anime.getReleaseYear())
                .hasFieldOrPropertyWithValue("shikiId", anime.getShikiId())
                .hasFieldOrPropertyWithValue("duration", anime.getDuration())
                .hasFieldOrPropertyWithValue("episodesEnd", anime.getEpisodesEnd())
                .hasFieldOrPropertyWithValue("usrId", anime.getUsrId());
    }

    @Test
    void convert_AnimeDtoRqToAnime_ShouldConvertAllFieldsCorrectly() {
        // Given
        AnimeDtoRq dtoRq = createAnimeDtoRq();

        // When
        Anime anime = AnimeDtoConverter.convert(dtoRq);

        // Then
        assertThat(anime)
                .hasFieldOrPropertyWithValue("id", dtoRq.getId())
                .hasFieldOrPropertyWithValue("originalTitle", dtoRq.getOriginalTitle())
                .hasFieldOrPropertyWithValue("title", dtoRq.getTitle())
                .hasFieldOrPropertyWithValue("episodes", dtoRq.getEpisodes())
                .hasFieldOrPropertyWithValue("status", dtoRq.getStatus())
                .hasFieldOrPropertyWithValue("score", dtoRq.getScore())
                .hasFieldOrPropertyWithValue("endDate", dtoRq.getEndDate())
                .hasFieldOrPropertyWithValue("releaseDate", dtoRq.getReleaseDate())
                .hasFieldOrPropertyWithValue("shikiId", dtoRq.getShikiId())
                .hasFieldOrPropertyWithValue("duration", dtoRq.getDuration())
                .hasFieldOrPropertyWithValue("episodesEnd", dtoRq.getEpisodesEnd())
                .hasFieldOrPropertyWithValue("usrId", dtoRq.getUsrId())
                .hasFieldOrPropertyWithValue("releaseYear", dtoRq.getReleaseDate().getYear());
    }

    @Test
    void convert_AnimeToAnimeDtoRs_WhenAnimeIsNull_ShouldThrowException() {
        // When & Then
        assertThatThrownBy(() -> AnimeDtoConverter.convert((Anime) null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void convert_AnimeDtoRqToAnime_WhenDtoRqIsNull_ShouldThrowException() {
        // When & Then
        assertThatThrownBy(() -> AnimeDtoConverter.convert((AnimeDtoRq) null))
                .isInstanceOf(NullPointerException.class);
    }
}