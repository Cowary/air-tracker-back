package org.cowary.arttrackerback.entity.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "movie")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private Integer releaseYear;
    private Integer duration;
    @NotNull
    @NotBlank
    private String status;
    @NotNull
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpd;
    private Long usrId;
    private Integer integrationId;

    @Transient
    private String type = "Movie";

    public Movie(String originalTitle, String title, int releaseYear, Integer duration, Integer integrationId) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.integrationId = integrationId;
    }
}
