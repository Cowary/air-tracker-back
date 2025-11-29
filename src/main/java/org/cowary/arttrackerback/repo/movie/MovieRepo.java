package org.cowary.arttrackerback.repo.movie;

import org.cowary.arttrackerback.entity.movie.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepo extends CrudRepository<Movie, Long> {

    List<Movie> findAll();
    List<Movie> findAllByUsrId(Long usrId);
    Optional<Movie> findById(Long id);
    List<Movie> findByStatus(String status);
}
