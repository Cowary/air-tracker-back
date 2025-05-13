package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.dbCase.book.BookCrud;
import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.dbCase.ranobe.RanobeVolumeCrud;
import org.cowary.arttrackerback.dbCase.tv.TvSeasonsCrud;
import org.cowary.arttrackerback.entity.Media;
import org.cowary.arttrackerback.rest.dto.GameDto;
import org.cowary.arttrackerback.rest.dto.MediaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/title/view")
public class MediaListController {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    MangaCrud mangaCrud;
    @Autowired
    RanobeVolumeCrud ranobeVolumeCrud;
    @Autowired
    MovieCrud movieCrud;
    @Autowired
    GameCrud gameCrud;
    @Autowired
    BookCrud bookCrud;
    @Autowired
    TvSeasonsCrud tvSeasonsCrud;
    ModelMapper modelMapper;

    @GetMapping("/media")
    public ResponseEntity<List<MediaDto>> getMediaList(@RequestHeader long userId,
                                                       @RequestParam(required = false, defaultValue = "") String status
    ) {
        modelMapper = new ModelMapper();
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(userId, status));
        mediaList.addAll(mangaCrud.getAll(userId, status));
        mediaList.addAll(ranobeVolumeCrud.getAll(userId, status));
        mediaList.addAll(movieCrud.getAll(userId, status));
        mediaList.addAll(gameCrud.getAll(userId, status));
        mediaList.addAll(bookCrud.getAll(userId, status));
        mediaList.addAll(tvSeasonsCrud.getAll(userId, status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());
        List<MediaDto> mediaDtoList = mediaList.stream()
                .map(source -> modelMapper.map(source, MediaDto.class))
                .toList();
        return ResponseEntity.ok(mediaDtoList);
    }

    @GetMapping("/play")
    public ResponseEntity<List<GameDto>> getPlayList(@RequestHeader long userId,
                                                     @RequestParam(required = false, defaultValue = "") String status) {
        var gameList = gameCrud.getAll(userId, status).stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .toList();
        var gameDtoList = gameList.stream()
                .map(source -> modelMapper.map(source, GameDto.class))
                .toList();

        return ResponseEntity.ok(gameDtoList);
    }

    @GetMapping("/read")
    public ResponseEntity<List<MediaDto>> getReadList(@RequestHeader long userId,
                                                   @RequestParam(required = false, defaultValue = "") String status) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(mangaCrud.getAll(userId, status));
        mediaList.addAll(ranobeVolumeCrud.getAll(userId, status));
        mediaList.addAll(bookCrud.getAll(userId, status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());
        List<MediaDto> mediaDtoList = mediaList.stream()
                .map(source -> modelMapper.map(source, MediaDto.class))
                .toList();
        return ResponseEntity.ok(mediaDtoList);
    }

    @GetMapping("/watch")
    public ResponseEntity<List<MediaDto>> getWatchList(@RequestHeader long userId,
                                                    @RequestParam(required = false, defaultValue = "") String status) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(userId, status));
        mediaList.addAll(movieCrud.getAll(userId, status));
        mediaList.addAll(tvSeasonsCrud.getAll(userId, status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());
        List<MediaDto> mediaDtoList = mediaList.stream()
                .map(source -> modelMapper.map(source, MediaDto.class))
                .toList();
        return ResponseEntity.ok(mediaDtoList);
    }
}
