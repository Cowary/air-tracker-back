package org.cowary.arttrackerback.entity.tv;

import jakarta.persistence.*;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "tv")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tv extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    private Integer releaseYear;
    private Integer score;
    private Integer seasons;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpd;
    private Long usrId;
    private Integer integrationId;
    @Transient
    private String type = "Tv";

    public Tv(String originalTitle, String title, Integer releaseYear, Integer seasons, Integer integrationId) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.releaseYear = releaseYear;
        this.seasons = seasons;
        this.integrationId = integrationId;
    }
}
