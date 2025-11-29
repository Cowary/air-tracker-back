package org.cowary.arttrackerback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Comparator;

@Getter
@Setter
@ToString

public class Media {

    private String status;
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    private Long usrId;

    @JsonIgnore
    Comparator<Media> comparator = new Comparator<Media>() {
        @Override
        public int compare(Media o1, Media o2) {
            if(o1.getStatus().equals(o2.getStatus())) return compare(o2.getEndDate(), o1.getEndDate());
            else return compare(o1.getStatus(), o2.getStatus());
        }

        public int compare(String status1, String status2) {
            if(status1.equals("Dropped") && !status2.equals("Dropped")) return 1;
            else if(!status1.equals("Dropped") && status2.equals("Dropped")) return -1;
            else return status1.compareTo(status2);

        }

        public int compare(LocalDate date1, LocalDate date2) {
            if(date1 == null && date2 != null) return -1;
            else if(date1 != null && date2 == null) return 1;
            else if(date1 == null) return 0;
            else return date1.compareTo(date2);
        }
    };
}
