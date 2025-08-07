package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.game.Game;
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

    public static Game convert(GameDtoRs gameDtoRs) {
        return Game.builder()
                .id(gameDtoRs.getId())
                .title(gameDtoRs.getTitle())
                .endDate(gameDtoRs.getEndDate())
                .releaseDate(gameDtoRs.getReleaseDate())
                .releaseYear(gameDtoRs.getReleaseYear())
                .score(gameDtoRs.getScore())
                .status(gameDtoRs.getStatus())
                .usrId(gameDtoRs.getUsrId())
                .build();
    }
}
