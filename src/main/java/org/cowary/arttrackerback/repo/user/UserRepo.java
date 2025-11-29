package org.cowary.arttrackerback.repo.user;

import org.cowary.arttrackerback.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
}
