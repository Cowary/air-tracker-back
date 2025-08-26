package org.cowary.arttrackerback.security;


import lombok.Setter;
import org.cowary.arttrackerback.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        return UserDetailsImpl.build(user);
    }

    public Long getIdCurrentUser() {
//        var userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var user = loadUserByUsername(userDetail.getUsername());
//        return user.getId();
        return 3L;
    }

    public String getNameCurrentUser() {
        return userRepo.findByUsername(getName()).getUsername();
    }

    private String getName() {
        return "stub";
        //return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser() {
        return userRepo.findByUsername(getName());
    }

}
