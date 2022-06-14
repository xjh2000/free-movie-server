package org.xjh.freemovieserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xjh.freemovieserver.domain.model.UserInfo;
import org.xjh.freemovieserver.repository.UserRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo register(UserInfo userInfo) {
        Optional<UserInfo> userInfo1 = userRepository.findByUsername(userInfo.getUsername());
        if (userInfo1.isPresent()) {
            throw new ValidationException("用户名已存在");
        }
        return userRepository.save(userInfo);
    }
}
