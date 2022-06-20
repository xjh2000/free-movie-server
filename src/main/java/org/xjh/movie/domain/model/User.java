package org.xjh.movie.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjh
 * @date 6/18/2022 8:05 PM
 */
@Entity
@Table(name = "users")
@Cacheable
public class User extends PanacheEntity {
    public String username;

    public String password;
    public String mail;

    public Integer isAdmin;
    @ManyToMany
    public List<Role> roles = new ArrayList<>();
}
