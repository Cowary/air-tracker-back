package org.cowary.arttrackerback.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration()
public class AppConfig {
    @Value("${app.shiki.base_url}")
    public String shikiUrl;
    @Value("${app.shiki.anime.url}")
    public String animeUrl;
    @Value("${app.shiki.role.url}")
    public String roleUrl;
}
