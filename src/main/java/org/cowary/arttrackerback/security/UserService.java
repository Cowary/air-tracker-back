package org.cowary.arttrackerback.security;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.cowary.arttrackerback.repo.user.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserDetailsService {
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        log.debug("Found user by username: {}", username);
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .disabled(false)
                .build();
    }

    @Deprecated
    public Long getIdCurrentUser() {
//        var userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var user = loadUserByUsername(userDetail.getUsername());
//        return user.getId();
        return 3L;
    }
}
