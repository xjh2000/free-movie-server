package org.xjh.freemovieserver.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xjh
 * @date 6/15/2022 12:19 PM
 */
@RestController
@RequestMapping("hello")
@Slf4j
public class HelloRestController {

    @GetMapping("user")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String helloAdmin() {
        return "Hello Admin";
    }

}