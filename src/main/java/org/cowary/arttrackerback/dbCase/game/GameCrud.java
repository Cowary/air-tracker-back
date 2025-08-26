package org.cowary.arttrackerback.dbCase.game;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.game.Game;
import org.cowary.arttrackerback.repo.game.GameRepo;
import org.cowary.arttrackerback.security.UserService;
import org.cowary.arttrackerback.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GameCrud implements MediaCrud<Game> {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserService userService;

    public List<Game> getAllByUserId(long userId) {
        return gameRepo.findAllByUsrId(userId);
    }

    public Game findById(long id) {
        return gameRepo.findById(id).orElseThrow();
    }

    public Game save(Game game) {
        game.setLastUpd(LocalDate.now());
        game.setUsrId(userService.getIdCurrentUser());
        if(game.getReleaseDate() != null) game.setReleaseYear(DateUtil.getYear(game.getReleaseDate()));
        return gameRepo.save(game);
    }

    public void delete(Game game) {
        gameRepo.delete(game);
    }

    @Override
    public List<Game> getAll(long userId, String status) {
        if(status.equals("")) return gameRepo.findAllByUsrId(userId);
        else return gameRepo.findByStatus(status);
    }

    public void deleteById(long id) {
        gameRepo.deleteById(id);
    }
}
