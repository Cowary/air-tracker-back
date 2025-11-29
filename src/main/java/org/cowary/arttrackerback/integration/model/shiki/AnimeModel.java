package org.cowary.arttrackerback.integration.model.shiki;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnimeModel{

    private int id;
    private String name;
    private String russian;
    private ImageModel image;
    private StudioModel[] studios;
    private String url;
    private String kind;
    private String score;
    private String status;
    private int episodes;
    private int episodes_aired;
    private String aired_on;
    private String released_on;
    private String rating;
    private String franchise;
    private Integer duration;

    private RoleModel[] roleModels;

    public AnimeModel() {
    }
}
