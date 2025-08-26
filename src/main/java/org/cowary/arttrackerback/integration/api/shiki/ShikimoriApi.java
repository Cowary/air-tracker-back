package org.cowary.arttrackerback.integration.api.shiki;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShikimoriApi {
    public AnimeApi animeApi;
    public MangaApi mangaApi;
    public RanobeApi ranobeApi;


}
