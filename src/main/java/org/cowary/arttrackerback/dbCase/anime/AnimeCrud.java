package org.cowary.arttrackerback.dbCase.anime;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.repo.anime.AnimeRepo;
import org.cowary.arttrackerback.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AnimeCrud implements MediaCrud<Anime> {

    @Autowired
    private AnimeRepo animeRepo;
    @Autowired
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(AnimeCrud.class);


    public List<Anime> getAllByUserId(long userId) {
        return animeRepo.findAllByUsrId(userId);
    }

    public Anime save(Anime anime) {
        anime.setLastUpd(LocalDate.now());
        anime.setUsrId(3L);
        animeRepo.save(anime);
        logger.info("Было сохранено аниме id: " + anime.getId() + " название: " + anime.getTitle() + " пользователя: " + 3);
        return anime;
    }

    public Anime getById(long id) {
        return animeRepo.findById(id).orElseThrow();
    }

    public void deleteById(long id) {
        animeRepo.deleteById(id);
        logger.info("Было удалено аниме с id: " + id);
    }

    @Override
    public List<Anime> getAll(long userId, String status) {
        if(status.equals("")) return animeRepo.findAllByUsrId(userId);
        else return animeRepo.findByStatusAndUsrId(status, userId);
    }
}
