package org.xjh.movie.handle;

import io.quarkus.runtime.StartupEvent;
import org.xjh.movie.domain.model.Role;
import org.xjh.movie.domain.model.User;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author xjh
 * @date 6/20/2022 11:45 AM
 */
@Singleton
public class StartupHandle {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {

        // 添加一个一般用户和管理员用户

        // 1.添加角色
        Role admin = new Role();
        admin.role = "admin";
        Role user = new Role();
        user.role = "user";
        Set.of(admin, user).forEach((role -> role.persist()));

        // 2.用户的添加
        if (User.findByUserName("admin").isEmpty()) {
            User.register("admin", "admin", Set.of(admin, user));
        }
        if (User.findByUserName("xjh").isEmpty()) {
            User.register("xjh", "xjh", Set.of(user));
        }

    }
}
