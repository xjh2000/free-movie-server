package org.xjh.freemovieserver.listen;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.xjh.freemovieserver.domain.model.Role;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.repository.mongo.UserRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

/**
 * @author xjh
 * @date 6/16/2022 3:45 PM
 */
@Slf4j
@Component
@AllArgsConstructor
public class InitListener implements ApplicationListener<ApplicationStartedEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 初始化管理员用户
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role(Role.USER));
        roles.add(new Role(Role.ADMIN));
        user.setAuthorities(roles);
        // 判断是否存在管理员用户
        Optional<User> dbAdmin = userRepository.findByUsername("admin");
        if (dbAdmin.isEmpty()) {
            userRepository.save(user);
            log.info("db init success , add admin user");
        } else {
            log.info("db init success , admin user already exists");
        }

    }
}
