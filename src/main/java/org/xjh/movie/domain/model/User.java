package org.xjh.movie.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}
