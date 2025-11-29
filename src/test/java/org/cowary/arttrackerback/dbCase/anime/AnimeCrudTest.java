package org.cowary.arttrackerback.dbCase.anime;

import org.assertj.core.api.Assertions;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.repo.anime.AnimeRepo;
import org.cowary.arttrackerback.security.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AnimeCrudTest {

    @Mock
    private AnimeRepo animeRepo;
    @Mock
    private UserService userService;
    @InjectMocks
    private AnimeCrud animeCrud;

    @Test
    void getAllByUserId() {
        long userId = 1L;
        List<Anime> expectedList = Arrays.asList(getAnime(), getAnime());
        when(animeRepo.findAllByUsrId(userId)).thenReturn(expectedList);

        List<Anime> actualList = animeCrud.getAllByUserId(userId);
        Assertions.assertThat(actualList)
                .isNotNull()
                .hasSize(2)
                .containsAll(expectedList);
    }

    @Test
    void save() {
        var animeToSave = getAnime();
        long expectedUserId = 3L;
        when(userService.getIdCurrentUser()).thenReturn(expectedUserId);

        animeCrud.save(animeToSave);

        verify(animeRepo, times(1)).save(animeToSave);
        Assertions.assertThat(animeToSave.getLastUpd())
                .isNotNull()
                .isEqualTo(LocalDate.now());
        Assertions.assertThat(animeToSave.getUsrId())
                .isNotNull()
                .isEqualTo(expectedUserId);

        // Reset the mock to avoid UnnecessaryStubbingException for other tests
        reset(userService);
    }

    @Test
    void getById() {
        when(animeRepo.findById(3L)).thenReturn(Optional.of(getAnime()));

        Anime anime = animeCrud.getById(3L);
        Assertions.assertThat(anime.getId())
                .isNotNull()
                .isEqualTo(getAnime().getId());
        Assertions.assertThat(anime.getOriginalTitle())
                .isNotNull()
                .isEqualTo(getAnime().getOriginalTitle());
        Assertions.assertThat(anime.getTitle())
                .isNotNull()
                .isEqualTo(getAnime().getTitle());
        Assertions.assertThat(anime.getEpisodes())
                .isNotNull()
                .isEqualTo(getAnime().getEpisodes());
        Assertions.assertThat(anime.getStatus())
                .isNotNull()
                .isEqualTo(getAnime().getStatus());
        Assertions.assertThat(anime.getEndDate())
                .isNotNull()
                .isEqualTo(getAnime().getEndDate());
        Assertions.assertThat(anime.getReleaseDate())
                .isNotNull()
                .isEqualTo(getAnime().getReleaseDate());
        Assertions.assertThat(anime.getReleaseYear())
                .isNotNull()
                .isEqualTo(getAnime().getReleaseYear());
        Assertions.assertThat(anime.getShikiId())
                .isNotNull()
                .isEqualTo(getAnime().getShikiId());
        Assertions.assertThat(anime.getDuration())
                .isNotNull()
                .isEqualTo(getAnime().getDuration());
        Assertions.assertThat(anime.getEpisodesEnd())
                .isNotNull()
                .isEqualTo(getAnime().getEpisodesEnd());
        Assertions.assertThat(anime.getLastUpd())
                .isNotNull()
                .isEqualTo(getAnime().getLastUpd());
        Assertions.assertThat(anime.getUsrId())
                .isNotNull()
                .isEqualTo(getAnime().getUsrId());
        Assertions.assertThat(anime.getType())
                .isNotNull()
                .isEqualTo("Anime");
    }

    @Test
    void deleteById() {
        long idToDelete = 1L;
        animeCrud.deleteById(idToDelete);
        verify(animeRepo, times(1)).deleteById(idToDelete);
    }

    @Test
    void getAll() {
        long userId = 1L;
        var anime1 = getAnime();
        anime1.setStatus("Finish");
        var anime2 = getAnime();
        anime2.setStatus("Active");
        List<Anime> expectedList = List.of(anime1, anime2);
        when(animeRepo.findAllByUsrId(userId)).thenReturn(expectedList);
        var expectedStatus = "Active";

        List<Anime> expectedAnimeList = List.of(anime1, anime2);
        List<Anime> filteredExpectedAnimeList = List.of(anime2);

        when(animeRepo.findAllByUsrId(userId)).thenReturn(expectedAnimeList);
        when(animeRepo.findByStatusAndUsrId(expectedStatus, userId)).thenReturn(filteredExpectedAnimeList);

        // When
        List<Anime> resultWithEmptyStatus = animeCrud.getAll(userId, "");
        List<Anime> resultWithStatus = animeCrud.getAll(userId, expectedStatus);

        // Then
        Assertions.assertThat(resultWithEmptyStatus)
                .isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrderElementsOf(expectedAnimeList);

        Assertions.assertThat(resultWithStatus)
                .isNotNull()
                .hasSize(1)
                .containsExactlyInAnyOrderElementsOf(filteredExpectedAnimeList);
    }

    private Anime getAnime() {
        return Anime.builder()
                .id(2L)
                .originalTitle("orTitle")
                .title("привет")
                .episodes(26)
                .status("Finish")
                .score(9)
                .endDate(LocalDate.now())
                .releaseDate(LocalDate.now().minusYears(1))
                .releaseYear(LocalDate.now().minusYears(1).getYear())
                .shikiId(33)
                .duration(22222)
                .episodesEnd(25)
                .lastUpd(LocalDate.now().minusYears(1))
                .usrId(3L)
                .type("Anime")
                .build();
    }
}