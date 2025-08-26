package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.MovieRs;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.integration.api.kin.KinApi;
import org.cowary.arttrackerback.integration.model.kin.KinResultModel;
import org.cowary.arttrackerback.rest.converter.MovieConverter;
import org.cowary.arttrackerback.rest.dto.request.MovieDtoRq;
import org.cowary.arttrackerback.rest.dto.response.MovieDtoRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
public class MovieController implements TitleController<MovieDtoRs, MovieDtoRq>, FindController<MovieRs> {

    @Autowired
    private MovieCrud movieCrud;

    @Override
    @GetMapping("/movie")
    public ResponseEntity<List<MovieDtoRs>> getAllByUsrId(@RequestHeader long userId) {
        var movieList = movieCrud.getAllByUserId(userId);
        var movieDtoList = movieList.stream().map(MovieConverter::convert).toList();
        return ResponseEntity.ok(
                movieDtoList
        );
    }

    @Override
    @GetMapping("/movie/{titleId}")
    public ResponseEntity<MovieDtoRs> getTitle(@PathVariable long titleId) {
        var movie = movieCrud.findById(titleId);
        var movieDto = MovieConverter.convert(movie);
        return ResponseEntity.ok(
                movieDto
        );
    }

    @Override
    @PostMapping("/movie")
    public ResponseEntity<MovieDtoRs> postTitle(@RequestBody @Valid MovieDtoRq title) {
        var movie = MovieConverter.convert(title);
        movieCrud.save(movie);
        var rs = MovieConverter.convert(movie);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(rs);
    }

    @Override
    @PutMapping("/movie")
    public ResponseEntity<MovieDtoRs> putTitle(@RequestBody @Valid MovieDtoRq title) {
        var movie = MovieConverter.convert(title);
        movieCrud.save(movie);
        var rs = MovieConverter.convert(movie);
        return ResponseEntity.ok(rs);
    }

    @Override
    @DeleteMapping("/movie")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        movieCrud.deleteById(id);
        return ResponseEntity.ok(String.format("movie №%s deleted", id));
    }

    @Override
    @GetMapping("/movie/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        var mediaModelList = KinApi.filmApi().searchByKeyword(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (KinResultModel kinResultModel : mediaModelList) {
            var year = Integer.valueOf(kinResultModel.getYear().equals("null") ? "0" : kinResultModel.getYear());
            var fins = new Finds(kinResultModel.getNameEn(), kinResultModel.getNameRu(), kinResultModel.getRating(), 1, year, kinResultModel.getFilmId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/movie/getByServiceId")
    public ResponseEntity<MovieRs> getByIntegrationID(@RequestParam @NotNull int id) {
        var kinFilmModel = KinApi.filmApi().getById(id);
        var movie = new Movie(kinFilmModel.getNameOriginal(), kinFilmModel.getNameRu(), kinFilmModel.getYear(), kinFilmModel.getFilmLength(), kinFilmModel.getKinopoiskId());
        var dto = MovieConverter.convert(movie);
        return ResponseEntity.ok(new MovieRs(dto, kinFilmModel.getPosterUrl()));
    }

}
