package org.cowary.arttrackerback.entity.anime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.cowary.arttrackerback.util.DateUtil;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "anime")
@Builder
@AllArgsConstructor
public class Anime extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String originalTitle;
    @NotBlank
    private String title;
    private Integer episodes;
    @NotBlank
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate releaseDate;
    private Integer releaseYear;
    private Integer shikiId;
    private Integer duration;
    private Integer episodesEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private LocalDate lastUpd;
    private Long usrId;
    @Transient
    private String type = "Anime";

    public Anime(String originalTitle, String title, Integer episodes, LocalDate releaseDate, Integer shikiId, Integer duration) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.episodes = episodes;
        this.releaseDate = releaseDate;
        this.releaseYear = DateUtil.getYear(releaseDate);
        this.shikiId = shikiId;
        this.duration = duration;
    }

    public Anime(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Anime() {
    }
}
