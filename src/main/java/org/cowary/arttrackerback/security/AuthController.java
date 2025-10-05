package org.cowary.arttrackerback.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.cowary.arttrackerback.entity.security.AuthenticationReq;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody AuthenticationReq loginRequest) throws Exception {
        log.debug("Trying to create a new authentication token for user: {}", loginRequest.getUsername());
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            log.debug("Created a new authentication token for user: {}", loginRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Getter
    public static class JwtResponse {
        private final String token;

        public JwtResponse(String jwtToken) {
            this.token = jwtToken;
        }
    }
}