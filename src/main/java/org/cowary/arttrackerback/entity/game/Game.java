package org.cowary.arttrackerback.entity.game;

import jakarta.persistence.*;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "game")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate releaseDate;
    private Integer releaseYear;
    private Integer score;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private LocalDate lastUpd;
    private Long usrId;
    @Transient
    private String type = "Game";

}
