package org.xjh.freemovieserver.api;

import org.springframework.web.bind.annotation.*;
import org.xjh.freemovieserver.domain.model.UserInfo;
import org.xjh.freemovieserver.repo.UserRepository;
import reactor.core.publisher.Mono;


@RestController()
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Mono<UserInfo> register(@RequestBody UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @GetMapping("/getCurrentUser")
    public UserInfo getCurrentUser() {
        return new UserInfo("xjh", "123456");
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo) {
        return "login";
    }
}
