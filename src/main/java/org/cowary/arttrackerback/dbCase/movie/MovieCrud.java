package org.cowary.arttrackerback.dbCase.movie;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.repo.movie.MovieRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovieCrud implements MediaCrud<Movie> {

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    UserService userService;

    public List<Movie> getAllByUserId(long userId) {
        return movieRepo.findAllByUsrId(userId);
    }

    public Movie findById(long id) {
        return movieRepo.findById(id).orElseThrow();
    }

    public void save(Movie movie) {
        movie.setLastUpd(LocalDate.now());
//        movie.setUsrId(userService.getIdCurrentUser());
        movie.setUsrId(3L);
        movieRepo.save(movie);
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }

    @Override
    public List<Movie> getAll(long userId, String status) {
        if(status.equals("")) return movieRepo.findAllByUsrId(userId);
        else return movieRepo.findByStatus(status);
    }

    public void deleteById(long id) {
        movieRepo.deleteById(id);
    }
}
