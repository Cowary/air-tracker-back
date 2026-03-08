package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface TitleController<RS, RQ> {

    ResponseEntity<List<RS>> getAllByUsrId();

    ResponseEntity<RS> getTitle(@PathVariable long titleId);

    ResponseEntity<RS> postTitle(@RequestBody @Valid RQ title);

    ResponseEntity<RS> putTitle(@RequestBody @Valid RQ title);

    ResponseEntity<String> deleteTitle(@RequestHeader long id);

}