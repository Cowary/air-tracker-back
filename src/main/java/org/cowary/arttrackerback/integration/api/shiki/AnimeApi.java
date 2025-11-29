package org.cowary.arttrackerback.integration.api.shiki;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.cowary.arttrackerback.configuration.AppConfig;
import org.cowary.arttrackerback.integration.api.kin.TitleApi;
import org.cowary.arttrackerback.integration.model.shiki.AnimeModel;
import org.cowary.arttrackerback.integration.util.RestTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnimeApi extends TitleApi {

    @Autowired
    public AnimeApi(RestTemp restTemp, AppConfig appConfig) {
        super(restTemp, appConfig);
    }

    public List<AnimeModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, appConfig.getAnimeUrl(), AnimeModel[].class));
    }

    public AnimeModel getById(int id) {
        return super.getById(id, appConfig.getAnimeUrl(), AnimeModel.class);
    }
}
