package org.cowary.arttrackerback.entity.tv;

import jakarta.persistence.*;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "tv_seasons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TvSeason extends Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalTitle;
    private Integer number;
    private Integer episodes;
    private Integer episodesEnd;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpd;
    private Integer releaseYear;
//    @ManyToOne()
//    @Cascade({CascadeType.MERGE})
//    @JoinColumn(name = "tv_id")
//    private Tv tv;
    private Long usrId;
    private Integer integrationId;
    @Transient
    private String type = "Tv";
}
