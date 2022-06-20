package org.xjh.movie.domain.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author xjh
 * @date 6/18/2022 8:05 PM
 */
@Entity
@UserDefinition
@Table(name = "users")
public class User extends PanacheEntity {
    @Username
    public String username;
    @Password
    public String password;
    public String mail;
    @Roles
    @ManyToMany
    public Set<Role> roles = new HashSet<>();

    /**
     * Adds a new user in the database
     *
     * @param username the username
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param roles    the roles of the user
     */
    public static void register(String username, String password, Set<Role> roles) {
        User user = new User();
        user.username = username;
        user.password = BcryptUtil.bcryptHash(password);
        user.roles = roles;
        user.persist();
    }

    public static Optional<User> findByUserName(String username) {
        return Optional.ofNullable(find("username", username).firstResult());
    }
}
