package org.xjh.freemovieserver.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xjh.freemovieserver.domain.model.UserInfo;
import org.xjh.freemovieserver.service.UserService;
import reactor.core.publisher.Mono;


@RestController()
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserInfo register(@RequestBody @Validated UserInfo userInfo) {
        return userService.register(userInfo);
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
