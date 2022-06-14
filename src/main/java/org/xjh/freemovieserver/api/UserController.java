package org.xjh.freemovieserver.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.service.UserService;


@RestController()
@RequestMapping("/user")

public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody @Validated User user) {
        return userService.register(user);
    }

    @GetMapping("/getCurrentUser")
    public User getCurrentUser() {
        return new User();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return "login";
    }
}
