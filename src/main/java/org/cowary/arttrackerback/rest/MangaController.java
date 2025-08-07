package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.MangaRs;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.MangaModel;
import org.cowary.arttrackerback.rest.converter.MangaDtoConverter;
import org.cowary.arttrackerback.rest.dto.response.MangaDtoRs;
import org.cowary.arttrackerback.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/title")
@Setter
public class MangaController implements TitleController<MangaDtoRs, MangaDtoRs>, FindController<MangaRs> {

    @Autowired
    private MangaCrud mangaCrud;

    @Override
    @GetMapping("/manga")
    public ResponseEntity<List<MangaDtoRs>> getAllByUsrId(@RequestHeader long userId) {
        var mangaList = mangaCrud.findAllByUserId(userId);
        var mangaDtoList = mangaList.stream().map(MangaDtoConverter::convert).toList();
        return ResponseEntity.ok(
                mangaDtoList
        );
    }

    @Override
    @GetMapping("/manga/{titleId}")
    public ResponseEntity<MangaDtoRs> getTitle(@PathVariable long titleId) {
        var manga = mangaCrud.findById(titleId);
        var mangaDto = MangaDtoConverter.convert(manga);
        return ResponseEntity.ok(
                mangaDto
        );
    }

    @Override
    @PostMapping("/manga")
    public ResponseEntity<MangaDtoRs> postTitle(@RequestBody @Valid MangaDtoRs title) {
        var manga = MangaDtoConverter.convert(title);
        mangaCrud.save(manga);
        title.setId(manga.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/manga")
    public ResponseEntity<MangaDtoRs> putTitle(@RequestBody @Valid MangaDtoRs title) {
        var manga = MangaDtoConverter.convert(title);
        mangaCrud.save(manga);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/manga")
    public ResponseEntity<String> deleteTitle(@RequestHeader @NotNull long id) {
        mangaCrud.deleteById(id);
        return ResponseEntity.ok(String.format("Manga №%s deleted", id));
    }

    @Override
    @GetMapping("/manga/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        Objects.requireNonNull(keyword);
        var mediaModelList = ShikimoriApi.mangaApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (MangaModel mangaModel : mediaModelList) {
            var releaseDate = mangaModel.getAired_on() == null ? null : LocalDate.parse(mangaModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()).getYear();
            var fins = new Finds(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getScore(), mangaModel.getChapters(),
                    releaseDate, mangaModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/manga/getByServiceId")
    public ResponseEntity<MangaRs> getByIntegrationID(@RequestParam @NotNull int id) {
        var mangaModel = ShikimoriApi.mangaApi().getById(id);
        var manga = new Manga(
                mangaModel.getName(), mangaModel.getRussian(), mangaModel.getVolumes(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()), mangaModel.getId()
        );
        //TODO: переделать
        var mangaDto = MangaDtoConverter.convert(manga);
        return ResponseEntity.ok(
                new MangaRs(mangaDto, mangaModel.getImage().getOriginal())
        );
    }
}
