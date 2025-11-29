package org.cowary.arttrackerback.integration.model.shiki;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudioModel {
    private int id;
    private String name;
    private String filtered_name;
    private boolean real;
    private String image;

    public StudioModel() {
    }
}


