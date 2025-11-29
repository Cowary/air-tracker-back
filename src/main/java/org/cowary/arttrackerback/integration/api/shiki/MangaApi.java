package org.cowary.arttrackerback.integration.api.shiki;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.cowary.arttrackerback.configuration.AppConfig;
import org.cowary.arttrackerback.integration.api.kin.TitleApi;
import org.cowary.arttrackerback.integration.model.shiki.MangaModel;
import org.cowary.arttrackerback.integration.util.RestTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MangaApi extends TitleApi {

    @Autowired
    public MangaApi(RestTemp restTemp, AppConfig appConfig) {
        super(restTemp, appConfig);
    }

    public List<MangaModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, appConfig.getMangaUrl(), MangaModel[].class));
    }

    public MangaModel getById(int id) {
        MangaModel model = super.getById(id, appConfig.getMangaUrl(), MangaModel.class);
        model.setRoleModels(super.getRoleById(id, appConfig.getMangaUrl()));

        return model;
    }
}
