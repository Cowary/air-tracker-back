package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.game.Game;
import org.cowary.arttrackerback.rest.dto.request.GameDtoRq;
import org.cowary.arttrackerback.rest.dto.response.GameDtoRs;

@UtilityClass
public class GameConverter {

    public static GameDtoRs convert(Game game) {
        return GameDtoRs.builder()
                .id(game.getId())
                .title(game.getTitle())
                .endDate(game.getEndDate())
                .releaseDate(game.getReleaseDate())
                .releaseYear(game.getReleaseYear())
                .score(game.getScore())
                .status(game.getStatus())
                .usrId(game.getUsrId())
                .build();
    }

    public static Game convert(GameDtoRq source) {
        return Game.builder()
                .id(source.getId())
                .title(source.getTitle())
                .endDate(source.getEndDate())
                .releaseDate(source.getReleaseDate())
                .releaseYear(source.getReleaseYear())
                .score(source.getScore())
                .status(source.getStatus())
                .usrId(source.getUsrId())
                .build();
    }
}
