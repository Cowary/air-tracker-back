package org.cowary.arttrackerback.dbCase.tv;


import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.entity.tv.TvSeason;
import org.cowary.arttrackerback.repo.tv.TvSeasonsRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TvSeasonsCrud implements MediaCrud<TvSeason> {

    @Autowired
    TvSeasonsRepo tvSeasonsRepo;
    @Autowired
    TvCrud tvCrud;
    @Autowired
    UserService userService;

    public void save(TvSeason tvSeason, Tv tv) {
        tv.setLastUpd(LocalDate.now());
        tv.setUsrId(userService.getIdCurrentUser());
        tvSeason.setUsrId(userService.getIdCurrentUser());
        tvSeason.setLastUpd(LocalDate.now());
        tvSeasonsRepo.save(tvSeason);
    }

    public TvSeason save(TvSeason tvSeason) {
//        tvSeason.getTv().setLastUpd(LocalDate.now());
//        tvSeason.getTv().setUsrId(userService.getIdCurrentUser());
//        tvSeason.setUsrId(userService.getIdCurrentUser());
        tvSeason.setUsrId(3L);
        tvSeason.setLastUpd(LocalDate.now());
//        tvCrud.save(tvSeason.getTv());
        return tvSeasonsRepo.save(tvSeason);
    }

    @Override
    public List<TvSeason> getAll(long userId, String status) {
        List<TvSeason> tvSeasons;
        if(status.equals("")) tvSeasons = tvSeasonsRepo.findAllByUsrId(userId);
        else tvSeasons = tvSeasonsRepo.findAllByStatus(status);

        return tvSeasons;
    }

    public List<TvSeason> getAllByUserId(long userId) {
        return tvSeasonsRepo.findAllByUsrId(userId);
    }

    public TvSeason getById(long id) {
        return tvSeasonsRepo.findById(id).orElseThrow();
    }


    public void delete(TvSeason tvSeason) {
        tvSeasonsRepo.delete(tvSeason);
    }

    public void deleteById(long id) {
        tvSeasonsRepo.deleteById(id);
    }
}
