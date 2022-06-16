package org.xjh.freemovieserver.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.xjh.freemovieserver.domain.model.Role;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.repository.mongo.UserRepository;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;


/**
 * @author xjh
 */
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    public User register(User user) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser.isPresent()) {
            throw new ValidationException("用户名已存在");
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setModifiedAt(now);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role(Role.USER));
        user.setAuthorities(roles);
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String userId = jwt.getClaim("userId");
        log.info("userId: {}", userId);
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("用户不存在"));
    }
}
