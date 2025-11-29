package org.cowary.arttrackerback.entity.ranobe;

import jakarta.persistence.*;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.cowary.arttrackerback.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "ranobe")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ranobe extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    private Integer volumes;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate releaseDate;
    private Integer releaseYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Integer shikiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpd;
    private Long usrId;
    @Transient
    private String type = "Ranobe";

    public Ranobe(String originalTitle, String title, Integer volumes, LocalDate releaseDate, Integer shikiId) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.volumes = volumes;
        this.releaseDate = releaseDate;
        this.releaseYear = DateUtil.getYear(releaseDate);
        this.shikiId = shikiId;
    }
}
