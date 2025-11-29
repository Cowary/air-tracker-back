package org.cowary.arttrackerback.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.mediaRs.MediaRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface FindController<T extends MediaRs> {

    ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword);
    ResponseEntity<T> getByIntegrationID(@RequestParam @NotNull int id);

}
