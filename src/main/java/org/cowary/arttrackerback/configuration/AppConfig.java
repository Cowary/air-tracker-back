package org.cowary.arttrackerback.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
    @Value("${app.shiki.base_url}")
    String shikiUrl;
    @Value("${app.shiki.anime.url}")
    String animeUrl;
    @Value("${app.shiki.manga.url}")
    String mangaUrl;
    @Value("${app.shiki.ranobe.url}")
    String ranobeUrl;
    @Value("${app.shiki.role.url}")
    String roleUrl;
    @Value("${app.kino.base_url}")
    String kinoBaseUrl;
    @Value("${app.kino.token}")
    String kinoToken;
    @Value("${app.version}")
    String version;
}
