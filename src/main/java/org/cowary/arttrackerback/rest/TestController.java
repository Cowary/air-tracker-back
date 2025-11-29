package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    AnimeCrud animeCrud;

    @GetMapping("/heh")
    public Anime getById() {
        return animeCrud.getById(25);
    }
}

