package org.cowary.arttrackerback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private int integrationId;

    public Person(String nameEn, String nameRu, int integrationId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.integrationId = integrationId;
    }

    public Person() {
    }
}
