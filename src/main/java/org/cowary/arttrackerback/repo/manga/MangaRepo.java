package org.cowary.arttrackerback.repo.manga;

import org.cowary.arttrackerback.entity.manga.Manga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRepo extends CrudRepository<Manga, Long> {
    List<Manga> findAll();
    List<Manga> findAllByUsrId(long usrId);
    List<Manga> findAllByStatus(String status);
}
