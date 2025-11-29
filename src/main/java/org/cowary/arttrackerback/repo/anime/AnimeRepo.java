package org.cowary.arttrackerback.repo.anime;

import org.cowary.arttrackerback.entity.anime.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnimeRepo extends CrudRepository<Anime, Long> {

    List<Anime> findAll();
    List<Anime> findAllByUsrId(Long usrId);
    Optional<Anime> findById(Long id);
    List<Anime> findByStatus(String status);
    List<Anime> findByStatusAndUsrId(String status, Long usrId);
}
