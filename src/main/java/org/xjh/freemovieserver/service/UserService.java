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


    /**
     * 注册用户
     *
     * @param user user 需要注册的用户
     * @return user 注册后的用户
     */
    public User register(User user) {
        // 判断用户名是否已经存在
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser.isPresent()) {
            throw new ValidationException("用户名已存在");
        }
        // 设置用户创建时间
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setModifiedAt(now);
        // 设置用户密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 设置用户角色
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role(Role.USER));
        user.setAuthorities(roles);
        // 保存用户到数据库
        return userRepository.save(user);
    }

    /**
     * 请求头中携带token，获取用户信息
     *
     * @return 当前登录用户
     */
    public User getCurrentUser() {
        // 获取令牌
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Jwt jwt = (Jwt) authentication.getCredentials();
        // 获取用户唯一标识
        String userId = jwt.getClaim("userId");
        // TODO redis 缓存处理 减轻数据库压力

        // 获取用户
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("用户不存在"));
    }
}
