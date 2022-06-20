package org.xjh.movie.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends PanacheEntity {

    @ManyToMany(mappedBy = "roles")
    public List<User> users;

    public String role;
}