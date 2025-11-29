package org.cowary.arttrackerback.repo.game;

import org.cowary.arttrackerback.entity.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepo extends CrudRepository<Game, Long> {

    List<Game> findAll();
    List<Game> findAllByUsrId(Long usrId);
    Optional<Game> findById(Long id);
    List<Game> findByStatus(String status);
}
