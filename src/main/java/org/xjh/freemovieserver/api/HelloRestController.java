package org.xjh.freemovieserver.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> log.info("authority: {}", authority.getAuthority()));
        return "Hello User";
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String helloAdmin() {
        return "Hello Admin";
    }

}