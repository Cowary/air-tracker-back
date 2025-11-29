package org.cowary.arttrackerback.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResp {

    private String token;
    private Long id;


}
