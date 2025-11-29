package org.cowary.arttrackerback.entity.manga;

import jakarta.persistence.*;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.cowary.arttrackerback.util.DateUtil;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "manga")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manga extends Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    private Integer volumes;
    private Integer chapters;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate releaseDate;
    private Integer releaseYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    private Integer shikiId;
    private Integer volumesEnd;
    private Integer chaptersEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private LocalDate lastUpd;
    private Long usrId;
    @Transient
    private String type = "Manga";

    public Manga(String originalTitle, String title, Integer volumes, Integer chapters, LocalDate releaseDate, Integer shikiId) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.volumes = volumes;
        this.chapters = chapters;
        this.releaseDate = releaseDate;
        this.releaseYear = DateUtil.getYear(releaseDate);
        this.shikiId = shikiId;
        this.status = "Planned";
    }
}
