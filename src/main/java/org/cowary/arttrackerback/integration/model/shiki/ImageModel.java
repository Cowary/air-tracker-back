package org.cowary.arttrackerback.integration.model.shiki;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageModel {
    private String original;
    private String preview;
    private String x96;
    private String x48;

    public ImageModel() {
    }
}
