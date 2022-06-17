package org.xjh.freemovieserver.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xjh.freemovieserver.domain.dto.UserDto;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.service.UserService;


/**
 * @author xjh
 */
@RestController()
@RequestMapping("/user")
@Slf4j
public class UserApi {

    private final UserService userService;

    private final ConversionService conversionService;

    public UserApi(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User register(@RequestBody @Validated User user) {
        return userService.register(user);
    }

    @GetMapping("/getCurrentUser")
    public UserDto getCurrentUser() {

        User user = userService.getCurrentUser();
        return conversionService.convert(user, UserDto.class);
    }

}
