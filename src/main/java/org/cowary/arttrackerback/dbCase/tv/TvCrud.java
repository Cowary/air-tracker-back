package org.cowary.arttrackerback.dbCase.tv;

import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.repo.tv.TvRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TvCrud  {

    @Autowired
    TvRepo tvRepo;
    @Autowired
    UserService userService;

    public List<Tv> getAllByUserId(long userId) {
        return tvRepo.findAllByUsrId(userId);
    }

    public Tv findById(long id) {
        return tvRepo.findById(id).orElseThrow();
    }

    public Tv findByOriginalTitle(String originalTitle) {
        return tvRepo.findByOriginalTitle(originalTitle).orElse(null);
    }

    public Tv findByOriginalTitleAndUserId(String originalTitle) {
        return tvRepo.findByOriginalTitle(originalTitle).orElse(null);
    }

    public void save(Tv tv) {
        tv.setLastUpd(LocalDate.now());
        tvRepo.save(tv);
    }

    public void delete(Tv tv) {
        tvRepo.delete(tv);
    }

    public void deleteById(long id) {
        tvRepo.deleteById(id);
    }
}
