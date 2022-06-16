package org.xjh.freemovieserver.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.service.UserService;

import java.text.ParseException;


/**
 * @author xjh
 */
@RestController()
@RequestMapping("/user")
@Slf4j
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User register(@RequestBody @Validated User user) {
        return userService.register(user);
    }

    @GetMapping("/getCurrentUser")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

}
