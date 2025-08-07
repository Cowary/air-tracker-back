package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.ranobe.RanobeCrud;
import org.cowary.arttrackerback.dbCase.ranobe.RanobeVolumeCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.RanobeRs;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.RanobeModel;
import org.cowary.arttrackerback.rest.converter.RanobeDtoConverter;
import org.cowary.arttrackerback.rest.dto.response.RanobeVolumeDtoRs;
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
public class RanobeController implements TitleController<RanobeVolumeDtoRs, RanobeVolumeDtoRs>, FindController<RanobeRs> {

    @Autowired
    private RanobeVolumeCrud ranobeVolumeCrud;
    @Autowired
    private RanobeCrud ranobeCrud;

    @Override
    @GetMapping("/ranobe")
    public ResponseEntity<List<RanobeVolumeDtoRs>> getAllByUsrId(@RequestHeader long userId) {
        var ranobeVolumesList = ranobeVolumeCrud.getAllByUserId(userId);
        var ranobeVolumesDtoList = ranobeVolumesList.stream().map(RanobeDtoConverter::convert).toList();
        return ResponseEntity.ok(
                ranobeVolumesDtoList
        );
    }

    @Override
    @GetMapping("/ranobe/{titleId}")
    public ResponseEntity<RanobeVolumeDtoRs> getTitle(@PathVariable long titleId) {
        var ranobe = ranobeVolumeCrud.getById(titleId);
        return ResponseEntity.ok(
                RanobeDtoConverter.convert(ranobe)
        );
    }

    @Override
    @PostMapping("/ranobe")
    public ResponseEntity<RanobeVolumeDtoRs> postTitle(@Valid @RequestBody RanobeVolumeDtoRs title) {
        var ranobe = RanobeDtoConverter.convert(title);
        ranobeVolumeCrud.save(ranobe);
        title.setId(ranobe.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/ranobe")
    public ResponseEntity<RanobeVolumeDtoRs> putTitle(@Valid @RequestBody RanobeVolumeDtoRs title) {
        ranobeVolumeCrud.save(RanobeDtoConverter.convert(title));
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/ranobe")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        ranobeVolumeCrud.deleteById(id);
        return ResponseEntity.ok(String.format("ranobe №%s deleted", id));
    }

    @Override
    @GetMapping("/ranobe/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        var mediaModelList = ShikimoriApi.ranobeApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (RanobeModel ranobeModel : mediaModelList) {
            var fins = new Finds(ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getScore(), ranobeModel.getChapters(),
                    LocalDate.parse(ranobeModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()).getYear(), ranobeModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/ranobe/getByServiceId")
    public ResponseEntity<RanobeRs> getByIntegrationID(@RequestParam @NotNull int id) {
        var actualRanobe = ranobeCrud.findByShikiIdAndUserId(id);
        var ranobeModel = ShikimoriApi.ranobeApi().getById(id);
        var ranobe = new Ranobe(
                ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getVolumes(),
                LocalDate.parse(ranobeModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()), ranobeModel.getId()
        );
        var rs = Objects.requireNonNullElse(actualRanobe, ranobe);
        return ResponseEntity.ok(
                new RanobeRs(RanobeDtoConverter.convert(rs), ranobeModel.getImage().getOriginal())
        );

    }

}
