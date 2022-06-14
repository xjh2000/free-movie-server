package org.xjh.freemovieserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.repository.UserRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User userInfo) {
        Optional<User> userInfo1 = userRepository.findByUsername(userInfo.getUsername());
        if (userInfo1.isPresent()) {
            throw new ValidationException("用户名已存在");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }
}
