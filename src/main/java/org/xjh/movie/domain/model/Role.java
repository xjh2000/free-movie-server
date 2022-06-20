package org.xjh.movie.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.RolesValue;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends PanacheEntity {

    @ManyToMany(mappedBy = "roles")
    public Set<User> users;
    @RolesValue
    public String role;

    public static Role findByRoleString(String role) {
        return find("role", role).firstResult();
    }
}