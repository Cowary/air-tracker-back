package org.cowary.arttrackerback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cowary.arttrackerback.security.User;

import java.util.Set;

@Getter
@Setter
@ToString

@Entity(name = "rle")
public class Role {

    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

//    @Override
//    public String getAuthority() {
//        return getName();
//    }


}
