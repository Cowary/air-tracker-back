package org.cowary.arttrackerback.integration.api.shiki;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.cowary.arttrackerback.configuration.AppConfig;
import org.cowary.arttrackerback.integration.api.kin.TitleApi;
import org.cowary.arttrackerback.integration.model.shiki.RanobeModel;
import org.cowary.arttrackerback.integration.util.RestTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RanobeApi extends TitleApi {

    @Autowired
    public RanobeApi(RestTemp restTemp, AppConfig appConfig) {
        super(restTemp, appConfig);
    }

    public List<RanobeModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, "URL_RANOBE", RanobeModel[].class));
    }

    public RanobeModel getById(int id) {
        RanobeModel model = super.getById(id, "URL_RANOBE", RanobeModel.class);
        model.setRoleModels(super.getRoleById(id, "URL_RANOBE"));

        return model;
    }
}
