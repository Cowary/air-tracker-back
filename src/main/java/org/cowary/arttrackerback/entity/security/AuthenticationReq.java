package org.cowary.arttrackerback.entity.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationReq {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
