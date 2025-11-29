package org.cowary.arttrackerback.integration.model.shiki;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonModel {
    private int id;
    private String name;
    private String russian;
    private ImageModel image;
    private String url;
}
