package org.cowary.arttrackerback.integration.api.kin;


import org.cowary.arttrackerback.integration.model.kin.KinFilmModel;
import org.cowary.arttrackerback.integration.model.kin.KinResultModel;
import org.cowary.arttrackerback.integration.model.kin.KinSearchModel;
import org.cowary.arttrackerback.integration.util.ApiUrl;
import org.cowary.arttrackerback.integration.util.RestTemp;
import org.cowary.arttrackerback.util.ProperUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilmApi {

    protected final HttpEntity<String> request;
    protected final RestTemp restTemp = new RestTemp();

    public FilmApi() {
        ProperUtil properUtil = new ProperUtil("kin.properties");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(
                "X-API-KEY",
                properUtil.getProp("api-key")
        );
        request = new HttpEntity<>(headers);
    }

    public KinFilmModel getById(int id) {
        URL urlFilm = new ApiUrl("https://kinopoiskapiunofficial.tech")
                .appendPathFromFile("api/v2.2/films")
                .appendPath(id)
                .build();

        return restTemp.getBody(urlFilm, request, KinFilmModel.class);
    }
    public List<KinResultModel> searchByKeyword(String keyword) {
        return searchByKeyword(keyword, List.of("FILM"));
    }

    public List<KinResultModel> searchByKeyword(String keyword, List<String> type) {
        URL urlSearch = new ApiUrl("https://kinopoiskapiunofficial.tech")
                .appendPathFromFile("api/v2.1/films/search-by-keyword")
                .addQuery("keyword", keyword)
                .addQuery("page", 1)
                .build();

        KinSearchModel kinSearchModel = restTemp.getBody(urlSearch, request, KinSearchModel.class);

        List<KinResultModel> resultModels = new ArrayList<>();
        int pageCount = Math.min(kinSearchModel.getPagesCount(), 5);
        if (pageCount == 0) pageCount = 1;
        for (int i = 1; i <= pageCount; i++) {
            resultModels.addAll(searchByKeyword(keyword, i));
        }

        return resultModels
                .stream()
                .filter(res -> type.contains(res.getType()))
                .collect(Collectors.toList());
    }

    private List<KinResultModel> searchByKeyword(String keyword, int page) {
        URL urlSearch = new ApiUrl("https://kinopoiskapiunofficial.tech")
                .appendPathFromFile("api/v2.1/films/search-by-keyword")
                .addQuery("keyword", keyword)
                .addQuery("page", page)
                .build();
        KinSearchModel kinSearchModel = restTemp.getBody(urlSearch, request, KinSearchModel.class);

        return new ArrayList<>(Arrays.asList(kinSearchModel.getKinResultModels()));
    }
}
