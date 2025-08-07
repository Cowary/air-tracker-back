package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.AnimeRs;
import org.cowary.arttrackerback.integration.api.shiki.AnimeApi;
import org.cowary.arttrackerback.integration.model.shiki.AnimeModel;
import org.cowary.arttrackerback.rest.converter.AnimeDtoConverter;
import org.cowary.arttrackerback.rest.dto.request.AnimeDtoRq;
import org.cowary.arttrackerback.rest.dto.response.AnimeDtoRs;
import org.cowary.arttrackerback.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
@Validated
public class AnimeController implements TitleController<AnimeDtoRs, AnimeDtoRq>, FindController<AnimeRs> {

    @Autowired
    private AnimeCrud animeCrud;
    @Autowired
    private AnimeApi animeApi;

    @Override
    @GetMapping("/anime")
    public ResponseEntity<List<AnimeDtoRs>> getAllByUsrId(@RequestHeader long userId) {
        var animeList = animeCrud.getAllByUserId(userId);
        var animeDtoList = animeList.stream().map(AnimeDtoConverter::convert).toList();
        return ResponseEntity.ok(
                animeDtoList
        );
    }

    @Override
    @GetMapping("/anime/{titleId}")
    public ResponseEntity<AnimeDtoRs> getTitle(@PathVariable long titleId) {
        var anime = animeCrud.getById(titleId);
        var animeDto = AnimeDtoConverter.convert(anime);
        return ResponseEntity.ok(
                animeDto
        );
    }

    @Override
    @PostMapping("/anime")
    public ResponseEntity<AnimeDtoRs> postTitle(@RequestBody @Valid AnimeDtoRq title) {
        var anime = AnimeDtoConverter.convert(title);
        animeCrud.save(anime);
        title.setId(anime.getId());
        var animeRs = AnimeDtoConverter.convert(anime);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animeRs);
    }

    @Override
    @PutMapping("/anime")
    public ResponseEntity<AnimeDtoRs> putTitle(@RequestBody @Valid AnimeDtoRq title) {
        var anime = AnimeDtoConverter.convert(title);
        animeCrud.save(anime);
        var animeRs = AnimeDtoConverter.convert(anime);
        return ResponseEntity.ok(animeRs);
    }

    @Override
    @DeleteMapping("/anime")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        animeCrud.deleteById(id);

        return ResponseEntity.ok(String.format("anime №%s deleted", id));
    }

    @Override
    @GetMapping("/anime/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        var animeModelList = animeApi.searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (AnimeModel animeModel : animeModelList) {
            var releaseDate = animeModel.getAired_on() == null ? 0 : LocalDate.parse(animeModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()).getYear();
            var fins = new Finds(animeModel.getName(), animeModel.getRussian(), animeModel.getScore(), animeModel.getEpisodes(),
                    releaseDate, animeModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/anime/getByServiceId")
    public ResponseEntity<AnimeRs> getByIntegrationID(@RequestParam @NotNull int id) {
        //TODO: переделать
        var animeModel = animeApi.getById(id);
        var anime = new Anime(
                animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), animeModel.getId(), animeModel.getDuration()
        );
        anime.setUsrId(3L);
        var animeDto = AnimeDtoConverter.convert(anime);
        if (animeDto.getStatus() == null) {
            animeDto.setStatus("Planned");
        }
        return ResponseEntity.ok(
                new AnimeRs(animeDto, "https://dere.shikimori.me" + removeAfterJpg(animeModel.getImage().getOriginal()))
        );
    }

    public static String removeAfterJpg(String input) {
        int index = input.indexOf(".jpg");
        if (index != -1) {
            return input.substring(0, index + 4); // +4, потому что ".jpg" — 4 символа
        }
        return input; // если .jpg не найдено, вернуть исходную строку
    }
}
