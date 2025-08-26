package org.cowary.arttrackerback.entity.ranobe;

import jakarta.persistence.*;
import lombok.*;
import org.cowary.arttrackerback.entity.Media;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "ranobe_volume")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RanobeVolume extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalTitle;
    private Integer number;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private LocalDate lastUpd;
    private Integer integrationId;
//    @ManyToOne()
//    @Cascade({CascadeType.MERGE})
//    @JoinColumn(name = "ranobe_id")
//    private Ranobe ranobe;
    private Long usrId;
    private LocalDate releaseDate;
    @Transient
    private String type = "Ranobe";
}
