package org.cowary.arttrackerback.integration.api.kin;


import org.cowary.arttrackerback.configuration.AppConfig;
import org.cowary.arttrackerback.integration.model.shiki.RoleModel;
import org.cowary.arttrackerback.integration.util.ApiUrl;
import org.cowary.arttrackerback.integration.util.RestTemp;

import java.net.URL;

abstract public class TitleApi {
    protected final RestTemp restTemp;
    protected final AppConfig appConfig;

    public TitleApi(RestTemp restTemp, AppConfig appConfig) {
        this.restTemp = new RestTemp();
        this.appConfig = appConfig;
    }

    public <T> T searchByName(String keyword, String urlTitle, Class<T> responseType) {
        URL url = new ApiUrl(appConfig.shikiUrl).appendPathFromFile(urlTitle)
                .addQuery("search", keyword)
                .addQuery("limit", 5)
                .build();

        return restTemp.getBody(url, responseType);
    }

    public <T> T getById(int animeId, String urlTitle, Class<T> responseType) {
        URL urlAnime = new ApiUrl(appConfig.shikiUrl).appendPathFromFile(urlTitle)
                .appendPath(animeId)
                .build();

        return restTemp.getBody(urlAnime, responseType);
    }

    public RoleModel[] getRoleById(int id, String urlTitle) {
        URL urlRole = new ApiUrl(appConfig.shikiUrl).appendPathFromFile(urlTitle)
                .appendPath(id)
                .appendPathFromFile(appConfig.roleUrl)
                .build();
        return restTemp.getBody(urlRole, RoleModel[].class);
    }
}

