package org.cowary.arttrackerback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {

    private int code;
    private String message;
}
