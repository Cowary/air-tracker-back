package org.cowary.arttrackerback.dbCase.manga;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.repo.manga.MangaRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MangaCrud implements MediaCrud<Manga> {

    @Autowired
    MangaRepo mangaRepo;
    @Autowired
    UserService userService;

    public List<Manga> findAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return mangaRepo.findAllByUsrId(userId);
        else return mangaRepo.findAllByStatus(status);
    }

    public List<Manga> findAllByUserId(long userId) {
        return mangaRepo.findAllByUsrId(userId);
    }

    public Manga findById(long id) {
        return mangaRepo.findById(id).orElseThrow();
    }

    public Manga save(Manga manga) {
        manga.setLastUpd(LocalDate.now());
        manga.setUsrId(userService.getIdCurrentUser());
        return mangaRepo.save(manga);
    }

    @Override
    public List<Manga> getAll(long userId, String status) {
        if(status.equals("")) return mangaRepo.findAllByUsrId(userId);
        else return mangaRepo.findAllByStatus(status);
    }

    public void deleteById(long id) {
        mangaRepo.deleteById(id);
    }
}
