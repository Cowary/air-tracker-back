package org.cowary.arttrackerback.dbCase.anime;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.repo.anime.AnimeRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnimeCrud implements MediaCrud<Anime> {

    private AnimeRepo animeRepo;
    private UserService userService;

    public List<Anime> getAllByUserId(long userId) {
        return animeRepo.findAllByUsrId(userId);
    }

    public Anime save(Anime anime) {
        anime.setLastUpd(LocalDate.now());
        anime.setUsrId(3L);
        animeRepo.save(anime);
        log.info("Было сохранено аниме id: {} название: {} пользователя: " + 3, anime.getId(), anime.getTitle());
        return anime;
    }

    public Anime getById(long id) {
        return animeRepo.findById(id).orElseThrow();
    }

    public void deleteById(long id) {
        animeRepo.deleteById(id);
        log.info("Было удалено аниме с id: {}", id);
    }

    @Override
    public List<Anime> getAll(long userId, String status) {
        if (status.isEmpty()) {
            return animeRepo.findAllByUsrId(userId);
        } else {
            return animeRepo.findByStatusAndUsrId(status, userId);
        }
    }
}
